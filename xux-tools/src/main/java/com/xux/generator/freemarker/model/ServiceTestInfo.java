package com.xux.generator.freemarker.model;

import lombok.Data;

@Data
public class ServiceTestInfo {
    private String className;

    private String packageStr;

    private ServiceImplInfo serviceImplInfo;
}
