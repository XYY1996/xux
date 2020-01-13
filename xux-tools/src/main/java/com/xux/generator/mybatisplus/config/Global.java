package com.xux.generator.mybatisplus.config;

import com.baomidou.mybatisplus.generator.config.GlobalConfig;

public class Global {

    private static String AUTHOR = "xuyy5";
    private static String DIR = "user.dir";

    private static String OUTPUT_MOUDLE = "xux-dao";
    private static String OUTPUT_PARENT_PACKAGE = "com.xux.dao";

    public static GlobalConfig getGlobalConfig() {

        String outputDir = getJavaDir();

        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(outputDir);
        globalConfig.setAuthor(AUTHOR);
        globalConfig.setOpen(false);
        // globalConfig.setSwagger2(true); 实体属性 Swagger2 注解
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
