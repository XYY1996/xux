package com.xux.generator.freemarker.framework.model;

import lombok.Data;

@Data
public class ColumnInfo {
    private String name;
    private String type;
    private String remark;
    private int len;
    private int precision;
}
