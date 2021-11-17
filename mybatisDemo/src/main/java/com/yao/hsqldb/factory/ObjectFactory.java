package com.yao.hsqldb.factory;

import java.util.List;
import java.util.Properties;

/**
 * @author xiaoK
 * @date 2021/11/17
 */
public interface ObjectFactory {
    <T> T create(Class<T> type);

    <T> T create(Class<T> type, List<Class<?>> constructorArgType, List<Object> consturctorArgs);

    void setProperties(Properties properties);

    <T> boolean isCollection(Class<T> type);

}
