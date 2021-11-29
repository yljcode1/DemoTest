package com.yao.hsqldb.api;

import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaClass;
import org.junit.Test;

/**
 * @author xiaoK
 * @date 2021/11/26
 */
public class TestMybatisAPI {
    @Test
    public void testMetaClass(){
        MetaClass metaClass = MetaClass.forClass(String.class, new DefaultReflectorFactory());
        String[] getterNames = metaClass.getGetterNames();
        System.out.println(getterNames.toString());
    }
}
