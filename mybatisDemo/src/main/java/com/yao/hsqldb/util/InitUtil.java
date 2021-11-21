package com.yao.hsqldb.util;

import com.yao.hsqldb.factory.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.SqlSessionManager;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * 获取到sqlSession的两种方法
 */
public class InitUtil {
    @Test
    public void testMybatis() throws IOException {
        //获取SqlSession方法1
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = factory.openSession();
        //通过SqlSession或取到Mapper对象
        User mapper = sqlSession.getMapper(User.class);

        //获取SqlSession方法二
        SqlSessionManager sqlSessionManager = SqlSessionManager.newInstance(inputStream);
        SqlSession sqlSession1 = sqlSessionManager.openSession();
        User mapper1 = sqlSession1.getMapper(User.class);
        String uuid = mapper1.getUuid();
        System.out.println(uuid);
    }
}
