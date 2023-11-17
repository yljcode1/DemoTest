package com.yao.springtest.blbl.hm.ch05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


/**
 * Spring的生命周期
 *
 * @date: 2023-11-17
 * @author: yao
 */
@Component
public class LifeCycleBean {
    private static final Logger log = LoggerFactory.getLogger(LifeCycleBean.class);

    public LifeCycleBean() {
        log.info("lifecycleBean构造");
    }

    @Autowired
    public void autowired(@Value("${JAVA_HOME}") String javaHome) {
        log.info("依赖注入:{}", javaHome);
    }

    @PostConstruct
    public void init() {
        log.info("初始化");
    }

    @PreDestroy
    public void destroy() {
        log.info("销毁");
    }
}
