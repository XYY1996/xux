package com.xux.generator.freemarker.handler.impl;

import com.xux.generator.freemarker.Constants;
import com.xux.generator.freemarker.config.Configuration;
import com.xux.generator.freemarker.handler.BaseHandler;
import com.xux.generator.freemarker.model.ManagerInfo;

import java.io.File;

public class ManagerHandler extends BaseHandler<ManagerInfo> {

    public ManagerHandler(String ftlName, ManagerInfo info){
        this.ftlName = ftlName;
        this.info = info;
        this.savePath = Configuration.getString("base.baseDir") + File.separator
                + Configuration.getString("manager.path") + File.separator + info.getClassName()
                + Constants.FILE_SUFFIX_JAVA;

    }

    @Override
    public void combileParams(ManagerInfo info) {
        this.param.put("packageStr", info.getPackageStr());
        this.param.put("voType", info.getVoType());
        this.param.put("entityDesc", info.getEntityDesc());
        this.param.put("className", info.getClassName());
        this.param.put("voClassName", info.getVoClassName());
        this.param.put("entityName", info.getVoInfo().getEntityInfo().getEntityName());
    }
}
