package com.xux.generator.freemarker.task;

import com.xux.generator.freemarker.framework.AbstractApplicationTask;
import com.xux.generator.freemarker.framework.context.ApplicationContext;
import com.xux.generator.freemarker.handler.BaseHandler;
import com.xux.generator.freemarker.handler.impl.ServiceImplHandler;
import com.xux.generator.freemarker.model.ServiceImplInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
@Slf4j
public class ServiceImplTask extends AbstractApplicationTask {

    private static String SERVICEIMPL_FTL = "template/ServiceImpl.ftl";

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
