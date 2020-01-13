/**
 * Project Name:xux
 * Package Name:com.xux.generator.mybatisplus.tool
 * Date:2019/12/23 17:38
 * <p>
 * All rights Reserved, Designed By MR.X_YY
 * Copyright: Copyright(C) 2016-2020
 * Company    xux  Co., Ltd.
 */
package com.xux.generator.mybatisplus.tool;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.*;

public class CustomizeTool {

    /**
     * 包名
     */
    private static final String PACKAGE_NAME = "com.xux.common";
    /**
     * 模块名称
     */
    private static final String MODULE_NAME = "xux-common";
    /**
     * 输出文件的路径
     */
    private static final String OUT_PATH = "D:\\project\\xux\\xux\\xux-common";
    /**
     * 代码生成者
     */
    private static final String AUTHOR = "xuyy";

    /**
     * JDBC相关配置
     */
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://10.21.19.72:3309/base?useUnicode=true&characterEncoding=UTF-8&socketTimeout=60000";
    private static final String USER_NAME = "imcdev";
    private static final String PASSWORD = "migu123456";

    public static void generator() {
        // 自定义需要填充的字段
        List<TableFill> tableFillList = new ArrayList<TableFill>();
        //如 每张表都有一个创建时间、修改时间
        //而且这基本上就是通用的了，新增时，创建时间和修改时间同时修改
        //修改时，修改时间会修改，
        //虽然像Mysql数据库有自动更新几只，但像ORACLE的数据库就没有了，
        //使用公共字段填充功能，就可以实现，自动按场景更新了。
        //如下是配置
        TableFill createField = new TableFill("gmt_create", FieldFill.INSERT);
        TableFill modifiedField = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);
        tableFillList.add(createField);
        tableFillList.add(modifiedField);

        String projectPath = System.getProperty("user.dir");
        String dir = projectPath + "/mybatis-plus-sample-generator/src/main/java";
        // 代码生成器
        AutoGenerator generator = new AutoGenerator();

        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig()
                .setOutputDir(OUT_PATH)// 输出目录
                .setFileOverride(true)// 是否覆盖文件
                .setActiveRecord(true)// 开启 activeRecord 模式
                .setEnableCache(false)// XML 二级缓存
                .setBaseResultMap(false)// XML ResultMap
                .setBaseColumnList(true)// XML columList
                .setAuthor(AUTHOR)
                // 自定义文件命名，注意 %s 会自动填充表实体属性！
                .setXmlName("%sMapper").setMapperName("%sDao");
                // .setServiceName("MP%sService")
                // .setServiceImplName("%sServiceDiy")
                // .setControllerName("%sAction");
        generator.setGlobalConfig(globalConfig);

        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig()
                // 数据库类型
                .setDbType(DbType.MYSQL)
                //.setTypeConvert(new MySqlTypeConvert() {
                // 自定义数据库表字段类型转换【可选】
                //@Override
                //  public DbColumnType processTypeConvert(String fieldType) {
                //    System.out.println("转换类型：" + fieldType);
                //    // if ( fieldType.toLowerCase().contains( "tinyint" ) ) {
                //    // return DbColumnType.BOOLEAN;
                //    // }
                //    return super.processTypeConvert(fieldType);
                //  }
                //})
                .setDriverName(DRIVER)
                .setUsername(USER_NAME)
                .setPassword(PASSWORD)
                .setUrl(URL);
        generator.setDataSource(dataSourceConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig()
                // .setCapitalMode(true)// 全局大写命名
                // .setDbColumnUnderline(true)// 全局下划线命名
                // .setTablePrefix(new String[]{"unionpay_"})// 此处可以修改为您的表前缀
                .setNaming(NamingStrategy.underline_to_camel)// 表名生成策略
                // .setInclude(new String[] {"user"}) // 需要生成的表
                // .setExclude(new String[]{"test"}) // 排除生成的表
                // 自定义实体，公共字段
                // .setSuperEntityColumns(new String[]{"test_id"})
                .setTableFillList(tableFillList)
                // 自定义实体父类
                // .setSuperEntityClass("com.baomidou.demo.base.BsBaseEntity")
                // 自定义 mapper 父类
                // .setSuperMapperClass("com.baomidou.demo.base.BsBaseMapper")
                // 自定义 service 父类
                // .setSuperServiceClass("com.baomidou.demo.base.BsBaseService")
                // 自定义 service 实现类父类
                // .setSuperServiceImplClass("com.baomidou.demo.base.BsBaseServiceImpl")
                // 自定义 controller 父类
                // .setSuperControllerClass("com.baomidou.demo.TestController")
                // 【实体】是否生成字段常量（默认 false）
                // public static final String ID = "test_id";
                .setEntityColumnConstant(true)
                // 【实体】是否为构建者模型（默认 false）
                // public User setName(String name) {this.name = name; return this;}
                .setEntityBuilderModel(true)
                // 【实体】是否为lombok模型（默认 false）<a href="https://projectlombok.org/">document</a>
                .setEntityLombokModel(true);
                // Boolean类型字段是否移除is前缀处理
                // .setEntityBooleanColumnRemoveIsPrefix(true)
                // .setRestControllerStyle(true)
                // .setControllerMappingHyphenStyle(true);
        generator.setStrategy(strategy);

        // 包配置
        PackageConfig packageConfig = new PackageConfig()
                .setModuleName(MODULE_NAME)
                // 自定义包路径
                .setParent(PACKAGE_NAME)
                // 这里是控制器包名，默认 web
                .setController("controller")
                .setXml("mapper").setMapper("dao");
        generator.setPackageInfo(packageConfig);

        // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        }.setFileOutConfigList(
            Collections.<FileOutConfig>singletonList(new FileOutConfig("/templates/mapper.xml.vm") {
                // 自定义输出文件目录
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return OUT_PATH + "/xml/" + tableInfo.getEntityName() + "Mapper.xml";
                }
            })
        );
        generator.setCfg(injectionConfig);

        // 自定义模板配置
        TemplateConfig templateConfig = new TemplateConfig()
                .setXml(null);
                // 自定义模板配置，模板可以参考源码 /mybatis-plus/src/main/resources/template 使用 copy
                // 至您项目 src/main/resources/template 目录下，模板名称也可自定义如下配置：
                // .setController("...");
                // .setEntity("...");
                // .setMapper("...");
                // .setXml("...");
                // .setService("...");
                // .setServiceImpl("...");

        // 执行生成
        generator.execute();
    }
}
