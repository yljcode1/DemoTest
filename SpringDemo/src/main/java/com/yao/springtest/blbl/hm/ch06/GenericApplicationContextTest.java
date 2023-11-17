package com.yao.springtest.blbl.hm.ch06;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.context.support.GenericApplicationContext;

/**
 * 一些常用的bean处理器
 *
 * @date: 2023-11-17
 * @author: yao
 */

public class GenericApplicationContextTest {
    public static void main(String[] args) {
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        applicationContext.registerBean("bean1", Bean1.class);
        applicationContext.registerBean("bean2", Bean2.class);
        applicationContext.registerBean("bean3", Bean3.class);
        // context默认不解析${}，需要替换解析器
        applicationContext.getDefaultListableBeanFactory().setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());
        applicationContext.registerBean(AutowiredAnnotationBeanPostProcessor.class); // @Autowired @Value
        applicationContext.registerBean(CommonAnnotationBeanPostProcessor.class);// @Resource @PostConstruct @PreDestroy
        // 初始化容器
        applicationContext.refresh();
        applicationContext.close();
    }
}
