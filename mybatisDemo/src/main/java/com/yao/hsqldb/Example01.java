package com.yao.hsqldb;

import com.yao.hsqldb.util.DBInitUtils;
import com.yao.hsqldb.util.IOUtils;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.SqlRunner;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ServiceLoader;

/**
 * @author xiaoK
 * @date 2021/11/17
 */
public class Example01 {
    private static Connection connection  ;
    static {
//        try {
//            Class.forName("org.hsqldb.jdbcDriver");
//            Reader reader = Resources.getResourceAsReader("database.properties");
//            Properties properties = new Properties();
//            properties.load(reader);
//            connection =  DriverManager.getConnection("jdbc:hsqldb:mem:mybatis",properties);
//            ScriptRunner scriptRunner = new ScriptRunner(connection);
//            scriptRunner.runScript(Resources.getResourceAsReader("create-table.sql"));
//            scriptRunner.runScript(Resources.getResourceAsReader("init-data.sql"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        connection = DBInitUtils.init();
    }


    /**通过DriverManager获取到Connection
     * 测试sqlRunner执行sql
     * @throws SQLException
     */
    @Test
    public void testHsqldbQuery() throws SQLException {
        SqlRunner sqlRunner = new SqlRunner(connection);
        List<Map<String, Object>> maps = sqlRunner.selectAll("select * from user");
        maps.forEach(System.out::println);
        sqlRunner.closeConnection();
    }

    /**
     * 通过DriverManager获取到Connection
     * 通过resultSet获取到metadata遍历字段和字段值
     * @throws SQLException
     */
    @Test
    public void testQuery() throws SQLException {
//        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery("select * from user");
//        ResultSetMetaData metaData = resultSet.getMetaData();
//        int columnCount = metaData.getColumnCount();
//        while (resultSet.next()){
//            for (int i = 1; i <= columnCount; i++) {
//                String columnName = metaData.getColumnName(i);
//                String columnVal = resultSet.getString(columnName);
//                System.out.println(columnName+":"+columnVal);
//            }
//            System.out.println("==========================");
//        }
//        IOUtils.closeQuietly(statement,connection);
        AccessSqlForEach(connection.createStatement(),"select * from user");
    }
    public void AccessSqlForEach(Statement statement,String sql) throws SQLException {
        ResultSet resultSet = statement.executeQuery(sql);
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (resultSet.next()){
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                String columnVal = resultSet.getString(columnName);
                System.out.println(columnName+":"+columnVal);
            }
            System.out.println("==========================");
        }
        IOUtils.closeQuietly(statement,connection);
    }
    /**
     * 通过初始化DataSource数据库获取到链接
     */
    @Test
    public void testDataSource(){
        DataSource dataSource = new UnpooledDataSource("org.hsqldb.jdbcDriver",
                "jdbc:hsqldb:mem:mybatis", "sa", "");
        try {
            connection =   dataSource.getConnection();
            Statement statement = connection.createStatement();
            AccessSqlForEach(statement,"select * from user");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 通过DataSource工厂获取链接
     */
    @Test
    public void testDataSourceFactory() throws Exception {
        UnpooledDataSourceFactory unpooledDataSourceFactory = new UnpooledDataSourceFactory();
        Properties properties = new Properties();
        InputStream configStream = Resources.getResourceAsStream("database.properties");
        properties.load(configStream);
        unpooledDataSourceFactory.setProperties(properties);
        DataSource dataSource = unpooledDataSourceFactory.getDataSource();
        connection = dataSource.getConnection();
        AccessSqlForEach(connection.createStatement(), "select * from user");
    }

    /**
     * 找出驱动
     */
    @Test
    public void testSPI() {
        for (Driver driver : ServiceLoader.load(Driver.class)) {
            System.out.println(driver.toString());
        }
    }
}
