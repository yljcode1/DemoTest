package com.yao.springtest.blbl.hm.ch02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * BeanFactory的test类，主要简单讲解BeanFactory后置处理器和Bean后置处理器
 *
 * @date: 2023-11-16
 * @author: yao
 */
public class DefaultListableContextApp {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext();
        classPathXmlApplicationContext.refresh();
        // 手动添加bean的定义,可以添加任意的类作为一个BeanDefinition,但是无法识别里面的@Bean注解等
        beanFactory.registerBeanDefinition("config", BeanDefinitionBuilder.genericBeanDefinition(Config.class).getBeanDefinition());

//        beanFactory.registerSingleton("configuration",ConfigurationClassPostProcessor.class);
//        beanFactory.registerBeanDefinition("configure", BeanDefinitionBuilder.genericBeanDefinition(ConfigurationClassPostProcessor.class).getBeanDefinition());
//        RootBeanDefinition def = new RootBeanDefinition(ConfigurationClassPostProcessor.class);
        // 添加一些常用的BeanFactory后置处理器，用来解析@Configuration等注解,补充了一些Bean的定义
        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);
        // 只有BeanFactoryPostProcessor有postProcessBeanFactory方法
        Map<String, BeanFactoryPostProcessor> beansOfType = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        beansOfType.forEach((x, y) -> y.postProcessBeanFactory(beanFactory));
        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
        // 准备好所有单例
        beanFactory.preInstantiateSingletons();
        // 获取Bean后处理器
        Map<String, BeanPostProcessor> beanPostprocess = beanFactory.getBeansOfType(BeanPostProcessor.class);
        //添加bean后处理器即可自己处理@Autowired等注解
        beanPostprocess.values().forEach(beanFactory::addBeanPostProcessor);
//        for (BeanPostProcessor beanPostProcessor : beanFactory.getBeanPostProcessors()) {
//        }
        // 为空,因为目前不解析@Autowired,要用Bean后置处理器,针对每个bean的生命周期各个阶段提供扩展@Autowired,@Resource等
        System.out.println(beanFactory.getBean(Bean1.class).getBean2());

        //  beanFactory不会主动去调用后置处理器，不会主动添加bean后处理器，不会主动初始化实例，不会解析${},#{}

    }

    @Configuration
    static class Config {
        @Bean
        public Bean1 bean1() {
            return new Bean1();
        }

        @Bean
        public Bean2 bean2() {
            return new Bean2();
        }
    }

    static class Bean1 {
        public Bean1() {
            System.out.println("构造bean1");
        }

        @Autowired
        private Bean2 bean2;

        public Bean2 getBean2() {
            return bean2;
        }
    }

    static class Bean2 {

    }
}


interface A {
    void aa();
}

abstract class B implements A {

    @Override
    public void aa() {
        System.out.println("aaa");
    }
}

abstract class B2 implements A {

}

class C extends B {

}

class D extends B2 {

    @Override
    public void aa() {
        System.out.println("B2,aaa");
    }
}

class E1 extends D {

}

class D2 {
    void hello(E1 e) {
        e.aa();
    }
}