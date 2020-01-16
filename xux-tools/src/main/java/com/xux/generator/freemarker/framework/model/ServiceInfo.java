package com.xux.generator.freemarker.framework.model;

import lombok.Data;

@Data
public class ServiceInfo {
    private String packageStr;

    private String getCommandType;

    private String listCommandType;

    private String batchCommandType;

    private String commandType;

    private String queryCommandType;

    private String voType;

    private String entityDesc;

    private String className;

    private String entityName;

    private String voClassName;

    private CommandInfo commandInfo;

    private VoInfo voInfo;
}
