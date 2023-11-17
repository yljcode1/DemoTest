package com.yao.springtest.blbl.hm.ch01;


import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 考察ApplicationContexnt和BeanFactory的关系，已经ApplicationContext扩展的其他功能
 *
 * @date: 2023-11-16
 * @author: yao
 */
@SpringBootApplication
public class ApplicationContextAndBeanFactory {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        // SpringBoot启动类返回的是一个ConfigurableApplicationContext类，这个类继承ApplicationContext
        ConfigurableApplicationContext context = SpringApplication.run(ApplicationContextAndBeanFactory.class);
        System.out.println(context);
        // BeanFactory 和ApplicationContext的关系
        // ApplicationContext 是BeanFactory的一个子接口，ApplicationContext组合了BeanFactory的能力
//        context.getBean("aaa");
        // BeanFactory 能干嘛,查看他的方法，singletonObjects, 这个属性里面都是实例化的bean
        Field singletonObjects = DefaultSingletonBeanRegistry.class.getDeclaredField("singletonObjects");
        singletonObjects.setAccessible(true);
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        Map<String, Object> map = (Map<String, Object>) singletonObjects.get(beanFactory);
        map.entrySet().stream().filter(x -> x.getKey().contains("component")).forEach((x) -> System.out.println(x + "=" + x.getValue()));

        Field singletonFactories = DefaultSingletonBeanRegistry.class.getDeclaredField("singletonFactories");
        singletonFactories.setAccessible(true);
        Map<String, Object> factoryMap = (Map<String, Object>) singletonFactories.get(beanFactory);

        //ApplicationContext 多实现的几个接口，提供不一样的能力
        // 国际化，需要有国际化的message文件,message_en.properties,message_zh.properties;等等
//        String hello = context.getMessage("hello", null, Locale.CHINA);

        // 获取资源
        Resource resource = context.getResource("");

        // 获取环境属性
        String property = context.getEnvironment().getProperty("java.home");

        // 监听
        context.getBean(Component1.class).sendMessage(context);
    }
}
