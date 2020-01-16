package com.xux.generator.freemarker.framework.model;

import lombok.Data;

@Data
public class ServiceImplInfo {
    private String className;

    private String packageStr;

    private String managerType;

    private String serviceType;

    private String getCommandType;

    private String listCommandType;

    private String batchCommandType;

    private String commandType;

    private String queryCommand;

    private String voType;

    private String entityDesc;

    private String lowerEntityName;

    private String entityName;
}
