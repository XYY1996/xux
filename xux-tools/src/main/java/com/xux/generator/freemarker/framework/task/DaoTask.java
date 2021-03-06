package com.xux.generator.freemarker.framework.task;

import com.xux.generator.freemarker.framework.Constants;
import com.xux.generator.freemarker.framework.config.Global;
import com.xux.generator.freemarker.framework.framework.AbstractApplicationTask;
import com.xux.generator.freemarker.framework.framework.context.ApplicationContext;
import com.xux.generator.freemarker.framework.handler.BaseHandler;
import com.xux.generator.freemarker.framework.handler.impl.DaoHandler;
import com.xux.generator.freemarker.framework.model.DaoInfo;
import com.xux.generator.freemarker.framework.model.MapperInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class DaoTask extends AbstractApplicationTask {

    private static String DAO_FTL = Global.getResourcePath("template/Dao.ftl");

    private List<DaoInfo> daoInfos;

    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        log.info("开始生成dao");

        daoInfos = (List<DaoInfo>) context.getAttribute("daoList");

        BaseHandler<DaoInfo> handler = null;
        for (DaoInfo daoInfo : daoInfos) {
            handler = new DaoHandler(DAO_FTL, daoInfo);
            handler.execute(context);
        }

        log.info("生成dao完成");
        return false;
    }

    @Override
    protected void doAfter(ApplicationContext context) throws Exception {
        super.doAfter(context);

        List<MapperInfo> mapperInfos = new ArrayList<MapperInfo>();
        MapperInfo mapperInfo = null;
        for (DaoInfo daoInfo : daoInfos) {
            mapperInfo = new MapperInfo();
            mapperInfo.setDaoInfo(daoInfo);
            mapperInfo.setEntityInfo(daoInfo.getEntityInfo());
            mapperInfo.setFileName(daoInfo.getEntityInfo().getEntityName() + Constants.MAPPER_XML_SUFFIX);
            mapperInfo.setNamespace(daoInfo.getPackageStr() + Constants.CHARACTER_POINT + daoInfo.getClassName());
            mapperInfos.add(mapperInfo);
        }
        context.setAttribute("mapperInfos", mapperInfos);
    }
}
