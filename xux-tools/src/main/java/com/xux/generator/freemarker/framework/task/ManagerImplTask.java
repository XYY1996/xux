package com.xux.generator.freemarker.framework.task;

import com.xux.generator.freemarker.framework.config.Global;
import com.xux.generator.freemarker.framework.framework.AbstractApplicationTask;
import com.xux.generator.freemarker.framework.framework.context.ApplicationContext;
import com.xux.generator.freemarker.framework.handler.BaseHandler;
import com.xux.generator.freemarker.framework.handler.impl.ManagerImplHandler;
import com.xux.generator.freemarker.framework.model.ManagerImplInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
@Slf4j
public class ManagerImplTask extends AbstractApplicationTask {

    private static String MANAGERIMPL_FTL = Global.getResourcePath("template/ManagerImpl.ftl");

    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        log.info("开始生成managerImpl");

        List<ManagerImplInfo> managerInfos = (List<ManagerImplInfo>) context.getAttribute("managerImplInfos");

        BaseHandler<ManagerImplInfo> handler = null;
        for (ManagerImplInfo managerImplInfo : managerInfos) {
            handler = new ManagerImplHandler(MANAGERIMPL_FTL, managerImplInfo);
            handler.execute(context);
        }

        log.info("生成managerImpl完成");
        return false;
    }
}
