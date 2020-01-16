package com.xux.generator.freemarker.single.util;

import com.xux.generator.freemarker.single.config.Global;
import com.xux.generator.freemarker.single.model.Column;
import freemarker.template.Template;
import org.springframework.util.StringUtils;

import java.io.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodeGenerateUtils {

    private final String tableName = "tm_project_quality_problem";
    private final String packageName = "com.evada.pm.process.manage";
    private final String tableAnnotation = "质量问题";
    private final String changeTableName = replaceUnderLineAndUpperCase(tableName);

    public void generate() throws Exception {
        try {
            Connection connection = Global.getConnection();
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getColumns(null, "%", tableName, "%");
            //生成Mapper文件
            generateMapperFile(resultSet);
            //生成Dao文件
            generateDaoFile(resultSet);
            //生成Repository文件
            generateRepositoryFile(resultSet);
            //生成服务层接口文件
            generateServiceInterfaceFile(resultSet);
            //生成服务实现层文件
            generateServiceImplFile(resultSet);
            //生成Controller层文件
            generateControllerFile(resultSet);
            //生成DTO文件
            generateDTOFile(resultSet);
            //生成Model文件
            generateModelFile(resultSet);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {

        }
    }

    private void generateModelFile(ResultSet resultSet) throws Exception {

        final String suffix = ".java";
        final String path = Global.getOutputDiskPath(changeTableName + suffix);
        final String templateName = "Model.ftl";

        List<Column> ColumnList = new ArrayList<>();
        Column Column = null;
        while (resultSet.next()) {
            //id字段略过
            if (resultSet.getString("COLUMN_NAME").equals("id")) continue;
            Column = new Column();
            //获取字段名称
            Column.setColumnName(resultSet.getString("COLUMN_NAME"));
            //获取字段类型
            Column.setColumnType(resultSet.getString("TYPE_NAME"));
            //转换字段名称，如 sys_name 变成 SysName
            Column.setChangeColumnName(replaceUnderLineAndUpperCase(resultSet.getString("COLUMN_NAME")));
            //字段在数据库的注释
            Column.setColumnComment(resultSet.getString("REMARKS"));
            ColumnList.add(Column);
        }
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("model_column", ColumnList);
        generateFileByTemplate(templateName, path, dataMap);

    }

    private void generateDTOFile(ResultSet resultSet) throws Exception {
        final String suffix = "DTO.java";
        final String path = Global.getOutputDiskPath(changeTableName + suffix);
        final String templateName = "DTO.ftl";
        generateFileByTemplate(templateName, path);
    }

    private void generateControllerFile(ResultSet resultSet) throws Exception {
        final String suffix = "Controller.java";
        final String path = Global.getOutputDiskPath(changeTableName + suffix);
        final String templateName = "Controller.ftl";
        generateFileByTemplate(templateName, path);
    }

    private void generateServiceImplFile(ResultSet resultSet) throws Exception {
        final String suffix = "ServiceImpl.java";
        final String path = Global.getOutputDiskPath(changeTableName + suffix);
        final String templateName = "ServiceImpl.ftl";
        generateFileByTemplate(templateName, path);
    }

    private void generateServiceInterfaceFile(ResultSet resultSet) throws Exception {
        final String prefix = "I";
        final String suffix = "Service.java";
        final String path = Global.getOutputDiskPath(changeTableName + suffix);
        final String templateName = "ServiceInterface.ftl";
        generateFileByTemplate(templateName, path);
    }

    private void generateRepositoryFile(ResultSet resultSet) throws Exception {
        final String suffix = "Repository.java";
        final String path = Global.getOutputDiskPath(changeTableName + suffix);
        final String templateName = "Repository.ftl";
        generateFileByTemplate(templateName, path);
    }

    private void generateDaoFile(ResultSet resultSet) throws Exception {
        final String suffix = "DAO.java";
        final String path = Global.getOutputDiskPath(changeTableName + suffix);
        final String templateName = "DAO.ftl";
        generateFileByTemplate(templateName, path);

    }

    private void generateMapperFile(ResultSet resultSet) throws Exception {
        final String suffix = "Mapper.xml";
        final String path = Global.getOutputDiskPath(changeTableName + suffix);
        final String templateName = "Mapper.ftl";
        generateFileByTemplate(templateName, path);

    }

    private void generateFileByTemplate(final String templateName, String filePath) throws Exception {
        Map<String, Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName, filePath, dataMap);
    }

    private void generateFileByTemplate(final String templateName, String filePath, Map<String, Object> dataMap) throws Exception {
        Template template = FreeMarkerTemplateUtils.getTemplate(templateName);
        FileOutputStream fos = new FileOutputStream(new File(filePath));
        dataMap.put("table_name_small", tableName);
        dataMap.put("table_name", changeTableName);
        dataMap.put("table_annotation", tableAnnotation);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);
        template.process(dataMap, out);
    }

    public String replaceUnderLineAndUpperCase(String str) {
        StringBuffer sb = new StringBuffer();
        sb.append(str);
        int count = sb.indexOf("_");
        while (count != 0) {
            int num = sb.indexOf("_", count);
            count = num + 1;
            if (num != -1) {
                char ss = sb.charAt(count);
                char ia = (char) (ss - 32);
                sb.replace(count, count + 1, ia + "");
            }
        }
        String result = sb.toString().replaceAll("_", "");
        return StringUtils.capitalize(result);
    }
}
