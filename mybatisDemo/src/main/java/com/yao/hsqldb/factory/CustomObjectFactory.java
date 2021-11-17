package com.yao.hsqldb.factory;

import com.yao.hsqldb.DefaultObjectFactory;

import java.util.UUID;

/**
 * @author xiaoK
 * @date 2021/11/17
 */
public class CustomObjectFactory extends DefaultObjectFactory {
    @Override
    public Object create(Class type) {
        if (type.equals(User.class)) {
            //实例化User类
            User user = (User) super.create(type);
            user.setUuid(UUID.randomUUID().toString());
            return user;
        }
        return super.create(type);
    }
}
