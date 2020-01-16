package com.xux.generator.freemarker.framework.model;

import lombok.Data;

@Data
public class CommandInfo {
    private String packageStr;

    private String voType;

    private String entityName;

    private String voClassName;

    private String className;

    private String getClassName;

    private String queryClassName;

    private String batchClassName;

    private String listClassName;
}
