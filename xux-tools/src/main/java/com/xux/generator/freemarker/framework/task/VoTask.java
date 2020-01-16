package com.xux.generator.freemarker.framework.task;

import com.xux.generator.freemarker.framework.Constants;
import com.xux.generator.freemarker.framework.config.Configuration;
import com.xux.generator.freemarker.framework.config.Global;
import com.xux.generator.freemarker.framework.framework.AbstractApplicationTask;
import com.xux.generator.freemarker.framework.framework.context.ApplicationContext;
import com.xux.generator.freemarker.framework.handler.BaseHandler;
import com.xux.generator.freemarker.framework.handler.impl.VoHandler;
import com.xux.generator.freemarker.framework.model.VoInfo;
import com.xux.generator.freemarker.framework.model.CommandInfo;
import com.xux.generator.freemarker.framework.model.EntityInfo;
import com.xux.generator.freemarker.framework.model.ManagerInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class VoTask  extends AbstractApplicationTask {

    private static String VO_FTL = Global.getResourcePath("template/Vo.ftl");
    private List<VoInfo> voList;

    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        log.info("开始生成vo");
        voList = (List<VoInfo>) context.getAttribute("voList");

        BaseHandler<VoInfo> handler = null;
        for (VoInfo voInfo : voList) {
            handler = new VoHandler(VO_FTL, voInfo);
            handler.execute(context);
        }
        log.info("结束生成vo");
        return false;
    }

    @Override
    protected void doAfter(ApplicationContext context) throws Exception {
        super.doAfter(context);

        // 组装ManagerInfo、CommandInfo
        List<ManagerInfo> managerInfos = new ArrayList<ManagerInfo>();
        List<CommandInfo> commandInfos = new ArrayList<CommandInfo>();
        ManagerInfo managerInfo = null;
        CommandInfo commandInfo = null;
        for (VoInfo voInfo : voList) {
            managerInfo = new ManagerInfo();
            commandInfo = new CommandInfo();

            EntityInfo entityInfo = voInfo.getEntityInfo();

            managerInfo.setClassName(entityInfo.getEntityName() + Constants.MANAGER_SUFFIX);
            managerInfo.setEntityDesc(entityInfo.getEntityDesc());
            managerInfo.setPackageStr(Configuration.getString("manager.package"));
            managerInfo.setVoClassName(voInfo.getClassName());
            managerInfo.setVoType(voInfo.getPackageStr() + Constants.CHARACTER_POINT + voInfo.getClassName());
            managerInfo.setVoInfo(voInfo);

            commandInfo.setEntityName(entityInfo.getEntityName());
            commandInfo.setPackageStr(Configuration.getString("command.package"));
            commandInfo.setVoClassName(voInfo.getClassName());
            commandInfo.setVoType(voInfo.getPackageStr() + Constants.CHARACTER_POINT + voInfo.getClassName());
            commandInfo.setClassName(entityInfo.getEntityName() + Constants.COMMAND_SUFFIX);
            commandInfo.setGetClassName("Get" + entityInfo.getEntityName() + Constants.COMMAND_SUFFIX);
            commandInfo.setQueryClassName(entityInfo.getEntityName() + "Query" + Constants.COMMAND_SUFFIX);
            commandInfo.setBatchClassName(entityInfo.getEntityName() + "Batch" + Constants.COMMAND_SUFFIX);
            commandInfo.setListClassName("List" + entityInfo.getEntityName() + Constants.COMMAND_SUFFIX);

            managerInfos.add(managerInfo);
            commandInfos.add(commandInfo);
        }
        context.setAttribute("managerInfos", managerInfos);
        context.setAttribute("commandInfos", commandInfos);

    }

}
