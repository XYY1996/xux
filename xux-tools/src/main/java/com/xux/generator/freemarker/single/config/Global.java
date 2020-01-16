package com.xux.generator.freemarker.single.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;

public class Global {

    private static String URL = "jdbc:mysql://10.21.19.72:3309/base?useUnicode=true&characterEncoding=UTF-8&socketTimeout=60000";
    private static String SCHEMA_NAME = "base";
    private static String DRIVE_NAME = "com.mysql.jdbc.Driver";
    private static String USER_NAME = "imcdev";
    private static String PASSWORD = "migu123456";

    private static String AUTHOR = "xuyy5";
    private static String CURRENT_DATE = "2017/05/03";
    private static String DIR = "user.dir";
    private static String DISK_PATH = "d:/";
    private static String OUTPUT_MOUDLE = "xux-dao";
    private static String OUTPUT_PARENT_PACKAGE = "com.xux.dao";

    public static String getResourcePath(String fileName) {
        return String.format("freemarker/single/%s", fileName);
    }

    public static Connection getConnection() throws Exception{
        Class.forName(DRIVE_NAME);
        Connection connection= DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        return connection;
    }

    public static String getOutputDiskPath(String fileName) {
        String outputDiskPath = String.format("%s%s", DISK_PATH, fileName);
        return outputDiskPath;
    }

    public static void initAuthorInfo(Map<String,Object> dataMap) {
        dataMap.put("author",AUTHOR);
        dataMap.put("date",CURRENT_DATE);
        dataMap.put("package_name",OUTPUT_PARENT_PACKAGE);
    }
}
