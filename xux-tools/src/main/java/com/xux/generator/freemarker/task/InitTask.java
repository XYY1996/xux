package com.xux.generator.freemarker.task;

import com.xux.generator.freemarker.Constants;
import com.xux.generator.freemarker.config.Configuration;
import com.xux.generator.freemarker.framework.AbstractApplicationTask;
import com.xux.generator.freemarker.framework.context.ApplicationContext;
import com.xux.generator.freemarker.model.ColumnInfo;
import com.xux.generator.freemarker.model.TableInfo;
import com.xux.generator.freemarker.util.DbUtil;
import com.xux.generator.freemarker.util.FileHelper;
import com.xux.generator.freemarker.util.PropertyUtil;
import com.xux.generator.freemarker.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class InitTask  extends AbstractApplicationTask {

    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        log.info("初始化任务");

        // 首先清空baseDir下的所有文件
        String baseDir = Configuration.getString("base.baseDir");
        FileHelper.deleteDirectory(baseDir);

        // 加载属性文件
        // 字段类型与属性类型的映射
        if (Configuration.getString("base.database").equals(Constants.DB_ORACLE)) {
            PropertyUtil.loadProp("columnType2PropType_oracle.properties");
        } else {
            PropertyUtil.loadProp("columnType2PropType.properties");
        }

        // 属性类型与包类名的映射
        PropertyUtil.loadProp("propType2Package.properties");

        // 属性类型与jdbc类型的映射，注意这里为了防止与上面冲突，属性类型前加了_
        PropertyUtil.loadProp("propType2JdbcType.properties");

        // 加载基本的7个字段到list
        String baseColumnsStr = Configuration.getString("base.baseColumns");
        String[] baseColumnsArr = baseColumnsStr.split(Constants.CHARACTER_COMMA);
        List<String> baseColumnList = new ArrayList<String>();
        for (String str : baseColumnsArr) {
            baseColumnList.add(str.toUpperCase());
        }
        context.setAttribute("baseColumns", baseColumnList);

        // 获取所有需要生成的表名
        List<String> tableList = StringUtil.splitStr2List(Configuration.getString("base.tableNames").toLowerCase(),
                Constants.CHARACTER_COMMA);
        log.info("需要生成的表：{}", tableList);

        // 对应的实体名
        List<String> entityNames = StringUtil.splitStr2List(Configuration.getString("base.entityNames"),
                Constants.CHARACTER_COMMA);

        // 实体对应的描述
        List<String> entityDescs = StringUtil.splitStr2List(Configuration.getString("base.entityDescs"),
                Constants.CHARACTER_COMMA);

        // 添加映射关系
        Map<String, String> table2Entity = new HashMap<String, String>();
        for (int i = 0; i < tableList.size(); i++) {
            table2Entity.put(tableList.get(i), entityNames.get(i));
        }

        Map<String, String> entity2Desc = new HashMap<String, String>();
        for (int i = 0; i < entityNames.size(); i++) {
            entity2Desc.put(entityNames.get(i), entityDescs.get(i));
        }

        // 放入上下文
        context.setAttribute("tableName.to.entityName", table2Entity);
        context.setAttribute("entityName.to.desc", entity2Desc);

        // 连接数据库
        Connection conn = null;
        ResultSet tableRS = null;
        ResultSet columnRS = null;

        try {
            conn = DbUtil.getConn();
            DatabaseMetaData dbMetaData = conn.getMetaData();

            String schemaPattern = Configuration.getString("base.schemaPattern");

            // 获取表的结果集
            if (Configuration.getString("base.database").equals(Constants.DB_ORACLE)) {
                tableRS = dbMetaData.getTables(null, schemaPattern, Constants.PERCENT, new String[] { "TABLE" });
            } else {
                tableRS = dbMetaData.getTables(null, schemaPattern, Constants.EMPTY_STR, new String[] { "TABLE" });
            }

            // 遍历
            Map<String, TableInfo> tableInfos = new HashMap<String, TableInfo>();
            while (tableRS.next()) {
                // 表名
                String tableName = tableRS.getString("TABLE_NAME").toLowerCase();
                log.info("数据库表名：{}", tableName);
                if (tableList.contains(tableName.toLowerCase())) {
                    log.info("*****************************");
                    log.info("tableName:{}", tableName);

                    TableInfo tableInfo = new TableInfo();
                    tableInfo.setName(tableName);

                    // 表注释
                    String tableRemark = tableRS.getString("REMARKS");
                    tableInfo.setRemark(tableRemark);
                    log.info("表{}的注释:{}", tableName, tableRemark);

                    // 表类型
                    String tableType = tableRS.getString("TABLE_TYPE");
                    tableInfo.setType(tableType);
                    log.info("表{}的类型:{}", tableName, tableType);

                    // 字段
                    // 获取列的结果集
                    if (Configuration.getString("base.database").equals(Constants.DB_ORACLE)) {
                        columnRS = dbMetaData.getColumns(null,
                                schemaPattern,
                                tableName.toUpperCase(),
                                Constants.PERCENT);
                    } else {
                        columnRS = dbMetaData.getColumns(null, schemaPattern, tableName, Constants.EMPTY_STR);
                    }

                    List<ColumnInfo> columnList = new ArrayList<ColumnInfo>();
                    while (columnRS.next()) {
                        String columnName = columnRS.getString("COLUMN_NAME").toLowerCase();
                        String columnType = columnRS.getString("TYPE_NAME").toLowerCase();
                        String columnRemark = columnRS.getString("REMARKS");
                        log.info("字段名称：{}, 字段类型：{}, 字段注释：{}", columnName, columnType, columnRemark);

                        int len = columnRS.getInt("COLUMN_SIZE");
                        // log.info("字段长度：{}", len);

                        int precision = columnRS.getInt("DECIMAL_DIGITS");
                        // log.info("字段类型精度：{}", precision);

                        if (columnName == null || "".equals(columnName)) {
                            continue;
                        }

                        ColumnInfo ci = new ColumnInfo();
                        ci.setName(columnName);
                        ci.setType(columnType);
                        ci.setRemark(columnRemark);
                        ci.setLen(len);
                        ci.setPrecision(precision);

                        columnList.add(ci);
                    }
                    log.info("*****************************");
                    tableInfo.setColumnList(columnList);
                    tableInfos.put(tableName, tableInfo);
                }

            }

            // 放入上下文
            context.setAttribute("tableInfos", tableInfos);

            if (tableInfos.size() == 0) {
                log.info("在数据库没有匹配到相应的表");
                return true;
            }
        } catch (Exception e) {
            log.info("初始化任务异常", e);
            e.printStackTrace();
        } finally {
            DbUtil.closeReso(conn, null, tableRS);
            DbUtil.closeReso(null, null, columnRS);
        }

        return false;
    }
}
