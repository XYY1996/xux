package com.xux.generator.freemarker.framework.handler.impl;

import com.xux.generator.freemarker.framework.Constants;
import com.xux.generator.freemarker.framework.config.Configuration;
import com.xux.generator.freemarker.framework.handler.BaseHandler;
import com.xux.generator.freemarker.framework.model.CommandInfo;
import com.xux.generator.freemarker.framework.util.StringUtil;

import java.io.File;

public class CommandHandler extends BaseHandler<CommandInfo> {

    private String className;

    public CommandHandler(String ftlName, CommandInfo info, String className){
        this.ftlName = ftlName;
        this.className = className;
        this.info = info;
        this.savePath = Configuration.getString("base.baseDir") + File.separator
                + Configuration.getString("command.path") + File.separator + this.className
                + Constants.FILE_SUFFIX_JAVA;
    }

    @Override
    public void combileParams(CommandInfo info) {
        this.param.put("packageStr", info.getPackageStr());
        this.param.put("voType", info.getVoType());
        this.param.put("entityName", info.getEntityName());
        this.param.put("voClassName", info.getVoClassName());
        this.param.put("className", this.className);
        this.param.put("serialVersionNum", StringUtil.generate16LongNum());
    }
}
