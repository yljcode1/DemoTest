package com.yao.springtest.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @date: 2023-11-08
 * @author: yao
 */
public class TestBeanFactory {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // bean的定义(class,scope,初始化，销毁)
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(Config.class).setScope("singleton").getBeanDefinition();
        beanFactory.registerBeanDefinition("config", beanDefinition);
//        AbstractBeanDefinition bean1bd = BeanDefinitionBuilder.genericBeanDefinition(Bean1.class).setScope("singleton").getBeanDefinition();
//        beanFactory.registerBeanDefinition("bean1", bean1bd);

//        // 给BeanFactory添加一些后处理器，给BeanFactory添加一些常用的后处理器，可以解析@Configuration
        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);
        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }

//
        // Bean 后置处理器，针对bean的生命周期的各个极端提供扩展，例如@Autowired,@Resource ...
        beanFactory.getBeansOfType(BeanFactoryPostProcessor.class).values().stream().forEach(beanFactoryPostProcessor ->
                beanFactoryPostProcessor.postProcessBeanFactory(beanFactory)
        );
        for (String name : beanFactory.getBeanDefinitionNames()) {
            System.out.println(name);
        }
        System.out.println(beanFactory.getBean(Bean1.class).getBean2());

        // 主动准备好所有的单例
        beanFactory.preInstantiateSingletons();
        // BeanFactory 不会主动去调用BeanFactory后处理器，不会主动添加Bean后处理器，不会主动初始化单例，不会解析beanFactory还不会解析${} 和#{}
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
        }

        @Autowired
        private Bean2 bean2;

        public Bean2 getBean2() {
            return bean2;
        }
    }

    static class Bean2 {
        public Bean2() {

        }
    }
}


