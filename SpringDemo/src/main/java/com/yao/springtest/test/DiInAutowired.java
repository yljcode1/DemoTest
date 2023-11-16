package com.yao.springtest.test;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @date: 2023-11-16
 * @author: yao
 */
public class DiInAutowired {

    public static void main(String[] args) {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
//        defaultListableBeanFactory.registerBeanDefinition("bean", BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition());
        defaultListableBeanFactory.registerSingleton("bean01", new Bean01());
        defaultListableBeanFactory.registerSingleton("bean01", new Bean01());
    }

    static class Bean01 {

    }
}
