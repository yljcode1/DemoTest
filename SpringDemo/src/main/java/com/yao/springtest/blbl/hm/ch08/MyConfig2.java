package com.yao.springtest.blbl.hm.ch08;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;

/**
 * @date: 2023-11-22
 * @author: yao
 */
@Slf4j
public class MyConfig2 implements InitializingBean, ApplicationContextAware {
    @Autowired
    public void setApplicationContext(ApplicationContext context) {
        log.debug("注入ApplicationContext");
    }

    @Bean
    public BeanFactoryPostProcessor postProcessor2() {
        return beanFactory -> log.debug("执行beanFactoryPostProcessor2处理器");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.debug("初始化");
    }
}
