package com.xux.generator.freemarker.framework.model;

import lombok.Data;

import java.util.List;
@Data
public class TableInfo {
    private String name;
    private String type;
    private String remark;
    private List<ColumnInfo> columnList;
}
