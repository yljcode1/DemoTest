package com.yao.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;

/**
 * 手动获取spring容器当中bean的工具类
 *
 * @date: 2022/6/21
 * @author: yao
 */
public class SpringContextHolder implements ApplicationContextAware {
    private static ApplicationContext applicationContext = null;

    /**
     * 从静态变量applicationContext中得到Bean，自动转型为所赋值对象的类型
     *
     * @param name beanName
     * @param <T>  要转换的类型
     * @return 返回bean
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        try {
            return (T) applicationContext.getBean(name);
        } catch (BeansException e) {
        }
        return null;
    }

    public static <T> T getBean(Class<T> requiredType) {
        try {
            return applicationContext.getBean(requiredType);
        } catch (BeansException e) {
        }
        return null;
    }

    /**
     * 实现ApplicationContextAware接口，注入context到静态变量中
     *
     * @param applicationContext applicationContext
     * @throws BeansException 抛出异常
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
    }
}
