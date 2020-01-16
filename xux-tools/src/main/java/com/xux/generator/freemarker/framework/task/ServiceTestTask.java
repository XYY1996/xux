package com.xux.generator.freemarker.framework.task;

import com.xux.generator.freemarker.framework.config.Global;
import com.xux.generator.freemarker.framework.framework.AbstractApplicationTask;
import com.xux.generator.freemarker.framework.framework.context.ApplicationContext;
import com.xux.generator.freemarker.framework.handler.BaseHandler;
import com.xux.generator.freemarker.framework.handler.impl.ServiceTestHandler;
import com.xux.generator.freemarker.framework.model.ServiceTestInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
@Slf4j
public class ServiceTestTask extends AbstractApplicationTask {

    private static String SERVICETEST_FTL = Global.getResourcePath("template/ServiceTest.ftl");

    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        log.info("开始生成service单元测试类。。。");

        List<ServiceTestInfo> list = (List<ServiceTestInfo>) context.getAttribute("serviceTestInfos");

        BaseHandler<ServiceTestInfo> baseHandler = null;
        for (ServiceTestInfo serviceTestInfo : list) {
            baseHandler = new ServiceTestHandler(SERVICETEST_FTL, serviceTestInfo);
            baseHandler.execute(context);
        }

        log.info("生成service单元测试类完成。。。");
        return false;
    }
}
