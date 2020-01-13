package com.xux.generator.mybatisplus.config;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;

public class DataSource {

    private static String URL = "jdbc:mysql://10.21.19.72:3309/base?useUnicode=true&characterEncoding=UTF-8&socketTimeout=60000";
    private static String SCHEMA_NAME = "base";
    private static String DRIVE_NAME = "com.mysql.jdbc.Driver";
    private static String USER_NAME = "imcdev";
    private static String PASSWORD = "migu123456";

    public static DataSourceConfig getDataSource() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(URL);
        dataSourceConfig.setSchemaName(SCHEMA_NAME);
        dataSourceConfig.setDriverName(DRIVE_NAME);
        dataSourceConfig.setUsername(USER_NAME);
        dataSourceConfig.setPassword(PASSWORD);
        return dataSourceConfig;
    }
}
