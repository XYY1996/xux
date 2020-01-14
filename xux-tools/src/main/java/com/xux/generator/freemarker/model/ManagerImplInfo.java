package com.xux.generator.freemarker.model;

import lombok.Data;

@Data
public class ManagerImplInfo {
    private String className;

    private String packageStr;

    private String daoType;

    private String entityType;

    private String managerType;

    private String voType;

    private String annotationName;

    private String managerClassName;

    private String daoClassName;

    private String daoVar;

    private String voClassName;

    private String entityClassName;

    private EntityInfo  entityInfo;

    private VoInfo voInfo;

    private DaoInfo daoInfo;

    private ManagerInfo managerInfo;

}
