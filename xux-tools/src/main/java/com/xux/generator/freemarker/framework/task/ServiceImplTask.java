package com.xux.generator.freemarker.framework.task;

import com.xux.generator.freemarker.framework.config.Global;
import com.xux.generator.freemarker.framework.framework.AbstractApplicationTask;
import com.xux.generator.freemarker.framework.framework.context.ApplicationContext;
import com.xux.generator.freemarker.framework.handler.BaseHandler;
import com.xux.generator.freemarker.framework.handler.impl.ServiceImplHandler;
import com.xux.generator.freemarker.framework.model.ServiceImplInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
@Slf4j
public class ServiceImplTask extends AbstractApplicationTask {

    private static String SERVICEIMPL_FTL = Global.getResourcePath("template/ServiceImpl.ftl");

    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        log.info("开始生成serviceImpl...");
        List<ServiceImplInfo> list = (List<ServiceImplInfo>) context.getAttribute("serviceImplInfos");

        BaseHandler<ServiceImplInfo> baseHandler = null;
        for (ServiceImplInfo info : list) {
            baseHandler = new ServiceImplHandler(SERVICEIMPL_FTL, info);
            baseHandler.execute(context);
        }

        log.info("结束生成serviceImpl。。。");
        return false;
    }

}
