package com.yao.hsqldb;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.jdbc.SqlRunner;
import org.junit.Test;

import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author xiaoK
 * @date 2021/11/17
 */
public class Example01 {
    private static Connection connection  ;
    static {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            Reader reader = Resources.getResourceAsReader("database.properties");
            Properties properties = new Properties();
            properties.load(reader);
            connection =  DriverManager.getConnection("jdbc:hsqldb:mem:mybatis",properties);
            ScriptRunner scriptRunner = new ScriptRunner(connection);
            scriptRunner.runScript(Resources.getResourceAsReader("create-table.sql"));
            scriptRunner.runScript(Resources.getResourceAsReader("init-data.sql"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testHsqldbQuery() throws SQLException {
        SqlRunner sqlRunner = new SqlRunner(connection);
        List<Map<String, Object>> maps = sqlRunner.selectAll("select * from user");
        maps.forEach(System.out::println);
        sqlRunner.closeConnection();
    }
}
