package com.baidu.ai.aip.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtil {
    private static JdbcUtil instance;
    Connection c;

    public static JdbcUtil getInstance() {
        if (instance == null) {
            instance = new JdbcUtil();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://192.168.43.136:3306/user?useUnicode=true&characterEncoding=utf8&useSSL=false&rewriteBatchedStatements=true", "root", "123456");
             return c;
        } catch (SQLException e) {
             e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
             e.printStackTrace();
        }
        return c;
    }
}