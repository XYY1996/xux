package com.xux.generator.freemarker.framework.model;

import lombok.Data;

@Data
public class ManagerInfo {
    private String packageStr;

    private String voType;

    private String entityDesc;

    private String className;

    private String voClassName;

    private VoInfo voInfo;
}
