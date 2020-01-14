package com.xux.generator.mybatisplus.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;

public class Global {

    private static String URL = "jdbc:mysql://10.21.19.72:3309/base?useUnicode=true&characterEncoding=UTF-8&socketTimeout=60000";
    private static String SCHEMA_NAME = "base";
    private static String DRIVE_NAME = "com.mysql.jdbc.Driver";
    private static String USER_NAME = "imcdev";
    private static String PASSWORD = "migu123456";

    private static String AUTHOR = "xuyy5";
    private static String DIR = "user.dir";
    private static String OUTPUT_MOUDLE = "xux-dao";
    private static String OUTPUT_PARENT_PACKAGE = "com.xux.dao";

    public static DataSourceConfig getDataSource() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig()
                .setUrl(URL)
                .setSchemaName(SCHEMA_NAME)
                .setDriverName(DRIVE_NAME)
                .setUsername(USER_NAME)
                .setPassword(PASSWORD)
                .setDbType(DbType.MYSQL);
        return dataSourceConfig;
    }

    public static GlobalConfig getGlobalConfig() {
        String outputDir = getJavaDir();

        GlobalConfig globalConfig = new GlobalConfig()
                .setOutputDir(outputDir)
                .setOpen(false)
                .setFileOverride(true)// 是否覆盖文件
                .setActiveRecord(true)// 开启 activeRecord 模式
                .setEnableCache(false)// XML 二级缓存
                .setBaseResultMap(false)// XML ResultMap
                .setBaseColumnList(true)// XML columList
                .setAuthor(AUTHOR);
        return globalConfig;
    }

    public static String getJavaDir() {
        String javaDir = String.format("%s/java", getMoudleDir());
        return javaDir;
    }

    public static String getMapperDir() {
        String mapperDir = String.format("%s/resources/mapper/", getMoudleDir());
        return mapperDir;
    }

    public static String getMoudleDir() {
        String projectPath = System.getProperty(DIR);
        String moudleDir = String.format("%s/%s/src/main", projectPath, OUTPUT_MOUDLE);
        return moudleDir;
    }

    public static String getParentPackage() {
        return OUTPUT_PARENT_PACKAGE;
    }

}
