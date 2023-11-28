package com.yao.springtest.blbl.hm.ch08;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;

/**
 * Aware接口回调
 *
 * @date: 2023-11-20
 * @author: yao
 */
@Slf4j
public class MyBean implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean {
    @Override
    public void setBeanName(String name) {
        log.debug("当前bean " + this + "名称叫做: " + name);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        log.debug("当前bean " + this + "beanFactory是: " + beanFactory);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.debug("当前bean " + this + "applicationContext是: " + applicationContext);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.debug("当前bean " + this + "初始化");
    }

    @Autowired
    public void aaa(ApplicationContext context) {
        log.debug("当前bean " + this + "使用@Autowired 容器是:" + context);
    }

    @PostConstruct
    public void init() {
        log.debug("当前bean " + this + "使用postConstruct 初始化");
    }
}
