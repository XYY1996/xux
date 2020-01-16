package com.xux.generator.freemarker.framework.model;

import lombok.Data;

@Data
public class ServiceTestInfo {
    private String className;

    private String packageStr;

    private ServiceImplInfo serviceImplInfo;
}
