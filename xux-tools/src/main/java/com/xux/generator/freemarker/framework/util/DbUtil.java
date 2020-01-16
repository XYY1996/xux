package com.xux.generator.freemarker.framework.util;

import com.xux.generator.freemarker.framework.config.Configuration;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.Properties;

@Slf4j
public class DbUtil {
    /**
     * 加载驱动
     */
    static {
        try {
            String driverName = Configuration.getString("jdbc.driverName");
            Class.forName(driverName);

            log.info("加载驱动成功：{}", driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * getConn:获取连接. <br/>
     *
     * @author qiyongkang
     * @return
     * @since JDK 1.6
     */
    public static Connection getConn() {
        Connection conn = null;
        try {
            String jdbcUrl = Configuration.getString("jdbc.url");
            String userName = Configuration.getString("jdbc.username");
            String password = Configuration.getString("jdbc.password");
            Properties props = new Properties();
            props.put("remarksReporting", "true");
            props.put("user", userName);
            props.put("password", password);
            conn = DriverManager.getConnection(jdbcUrl, props);
        } catch (SQLException e) {
            log.error("数据连接异常", e);
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * closeConn:关闭连接. <br/>
     *
     * @author qiyongkang
     * @param conn
     * @since JDK 1.6
     */
    public static void closeReso(Connection conn, Statement stat, ResultSet resultSet) {
        try {
            if (conn != null) conn.close();
            if (stat != null) stat.close();
            if (resultSet != null) resultSet.close();
            log.info("关闭资源成功。。。");
        } catch (SQLException e) {
            log.error("关闭连接异常", e);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(getConn());
    }
}
