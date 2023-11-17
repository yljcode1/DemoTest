package com.yao.springtest.blbl.hm.ch03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * Autowired 和Resource注解先后顺序
 *
 * @date: 2023-11-16
 * @author: yao
 */
public class AutowiredAndResourceOrderTest {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);
        beanFactory.registerBeanDefinition("config", BeanDefinitionBuilder.genericBeanDefinition(Config.class).getBeanDefinition());

        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessor = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor postProcessor : beanFactoryPostProcessor.values()) {
            postProcessor.postProcessBeanFactory(beanFactory);
        }
        Map<String, BeanPostProcessor> beanPostProcessor = beanFactory.getBeansOfType(BeanPostProcessor.class);
        beanFactory.addBeanPostProcessors(beanPostProcessor.values());
        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }

        System.out.println(beanFactory.getBean("inner"));
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

        @Bean
        public Bean3 bean3() {
            return new Bean3();
        }

        @Bean
        public Bean4 bean4() {
            return new Bean4();
        }
    }

    interface Inner {

    }

    static class Bean3 implements Inner {

    }

    static class Bean4 implements Inner {

    }

    static class Bean1 {
        @Autowired
        private Bean2 bean2;

        public Bean2 getBean2() {
            return bean2;
        }

        //@Qualifier("inner")
        @Autowired
        private Inner inner;


        public Inner getInner() {
            return inner;
        }
    }

    static class Bean2 {

    }
}
