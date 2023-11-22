package com.yao.springtest.blbl.hm.ch08;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

/**
 * @date: 2023-11-22
 * @author: yao
 */
@Slf4j
public class MyConfig1 {
    @Autowired
    public void setApplicationContext(ApplicationContext context) {
        log.debug("注入ApplicationContext");
    }

    @PostConstruct
    public void init() {
        log.debug("初始化");
    }

    @Bean
    public BeanFactoryPostProcessor postProcessor() {
        return beanFactory -> log.debug("初始化");
    }
}
