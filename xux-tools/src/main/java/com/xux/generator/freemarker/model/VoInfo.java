package com.xux.generator.freemarker.model;

import lombok.Data;

@Data
public class VoInfo {
    /**
     * 包路径
     */
    private String packageStr;

    /**
     * 类名
     */
    private String className;

    /**
     * 实体信息
     */
    private EntityInfo entityInfo;
}
