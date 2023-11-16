package com.yao.springtest;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 测试注解注入BeanFactory的方法
 *
 * @date: 2023-11-15
 * @author: yao
 */

public class AnnotationConfigTest {
    private static DefaultListableBeanFactory defaultListableBeanFactory;
    private static AnnotationConfigApplicationContext context;

    static {
        defaultListableBeanFactory = new DefaultListableBeanFactory();
        context = new AnnotationConfigApplicationContext(Config.class);
    }

    public static void main(String[] args) {
//        AnnotatedBeanDefinitionReader annotatedBeanDefinitionReader = new AnnotatedBeanDefinitionReader(context);
//        annotatedBeanDefinitionReader.registerBean(Bean01.class, Component.class);
    }

    @Configuration
    static class Config {
        @Bean
        public Bean01 bean01() {
            return new Bean01();
        }

        @Bean
        public Bean02 bean02() {
            return new Bean02();
        }
    }

    @Component
    static class Bean01 {
    }

    @Component
    static class Bean02 {
    }
}
