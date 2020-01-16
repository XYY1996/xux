package com.xux.generator.freemarker.framework.config;

public class Global {
    public static String getResourcePath(String fileName) {
        return String.format("freemarker/framework/%s", fileName);
    }
}
