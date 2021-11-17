package com.yao.hsqldb;

import com.yao.hsqldb.util.DBInitUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaClass;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.junit.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoK
 * @date 2021/11/17
 */
public class Example02 {
    @Test
    public void testMetaObject() {

        List<Integer> integers = new ArrayList() {{
            add(1);
            add(2);
        }};


        List<Order> orders = new ArrayList() {
            {
                add(new Order("order221341234", "<mybatis>图书"));
                add(new Order("order12331", "<angle>图书"));
            }
        };
        User user = new User(orders, "zhangsan", 3);
        //SystemMetaObject.forObject可以获取到子类子类的私有属性
        MetaObject metaObject = SystemMetaObject.forObject(user);
        MetaClass.forClass(User.class, new DefaultReflectorFactory());
        System.out.println(user.getOrders());
        System.out.println(metaObject.getValue("orders[0].goodsName"));
        System.out.println(metaObject.getValue("orders[1].goodsName"));
        Connection init = DBInitUtils.init();
        MetaObject metaObject1 = SystemMetaObject.forObject(init);
        ObjectFactory objectFactory = metaObject1.getObjectFactory();
    }

    @Data
    @AllArgsConstructor
    private static class User {
        List<Order> orders;
        String name;
        Integer age;
    }

    @Data
    @AllArgsConstructor
    private static class Order {
        String orderNo;
        String goodsName;
    }
}
