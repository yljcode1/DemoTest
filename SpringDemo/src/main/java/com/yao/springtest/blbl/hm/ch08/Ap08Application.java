package com.yao.springtest.blbl.hm.ch08;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

/**
 * Aware接口以及InitializingBean接口
 *
 * @date: 2023-11-20
 * @author: yao
 */
public class Ap08Application {
    public static void main(String[] args) {

        /**
         * 1、Aware接口用于注入一些与容器相关的信息，例如
         * BeanNameAware 注入bean的名字
         * BeanFactoryAware 注入BeanFactory容器
         * ApplicationContextAware 注入ApplicationContext容器
         * EmbeddedValueResolverAware ${}
         */
        GenericApplicationContext genericApplicationContext = new GenericApplicationContext();
        genericApplicationContext.registerBean("myBean", MyBean.class);
        genericApplicationContext.registerBean(AutowiredAnnotationBeanPostProcessor.class);
        genericApplicationContext.registerBean(CommonAnnotationBeanPostProcessor.class);
        genericApplicationContext.refresh();
        genericApplicationContext.close();
        /**
         * BeanFactoryAware、ApplicationContextAware、EmbeddedValueResolverAware的功能也能使用@Autowired就能实现，为啥需要Aware接口呢
         * 简单的说，@Autowired的解析需要用到bean后处理器，属于扩展功能
         *  而@Aware接口属于内置功能，不需要任何扩展，Spring就能识别到，在某些情况下，扩展功能会失效，而内置功能不会失效
         *
         */

    }
}
