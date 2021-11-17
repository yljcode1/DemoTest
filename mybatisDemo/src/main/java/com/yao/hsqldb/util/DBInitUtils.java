package com.yao.hsqldb.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author xiaoK
 * @date 2021/11/17
 */
public class DBInitUtils {
    private static Connection connection;

    /**
     * 使用mybatis自带的ScriptRunner来执行sql脚本
     * @return
     */
    public static Connection init(){
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("database.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Properties properties = new Properties();
        try {
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            connection =  DriverManager.getConnection("jdbc:hsqldb:mem:mybatis",properties);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ScriptRunner scriptRunner = new ScriptRunner(connection);
        try {
            scriptRunner.runScript(Resources.getResourceAsReader("create-table.sql"));
            scriptRunner.runScript(Resources.getResourceAsReader("init-data.sql"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
