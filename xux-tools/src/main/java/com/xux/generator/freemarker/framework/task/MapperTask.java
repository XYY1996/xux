package com.xux.generator.freemarker.framework.task;

import com.xux.generator.freemarker.framework.Constants;
import com.xux.generator.freemarker.framework.config.Configuration;
import com.xux.generator.freemarker.framework.config.Global;
import com.xux.generator.freemarker.framework.framework.AbstractApplicationTask;
import com.xux.generator.freemarker.framework.framework.context.ApplicationContext;
import com.xux.generator.freemarker.framework.handler.BaseHandler;
import com.xux.generator.freemarker.framework.handler.impl.MapperHandler;
import com.xux.generator.freemarker.framework.model.MapperInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
@Slf4j
public class MapperTask extends AbstractApplicationTask {

    private static String MAPPER_FTL = Configuration.getString("base.database")
            .equals(Constants.DB_ORACLE)
            ? Global.getResourcePath("template/Mapper_oracle.ftl")
            : Global.getResourcePath("template/Mapper.ftl");

    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        log.info("开始生成Mapper");

        List<MapperInfo> list = (List<MapperInfo>) context.getAttribute("mapperInfos");

        BaseHandler<MapperInfo> handler = null;
        for (MapperInfo mapperInfo : list) {
            handler = new MapperHandler(MAPPER_FTL, mapperInfo);
            handler.execute(context);
        }

        log.info("生成Mapper完成");
        return false;
    }

    @Override
    protected void doAfter(ApplicationContext context) throws Exception {
        super.doAfter(context);
    }
}
