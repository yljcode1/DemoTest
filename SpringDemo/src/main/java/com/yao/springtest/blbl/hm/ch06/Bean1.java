package com.yao.springtest.blbl.hm.ch06;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * @date: 2023-11-17
 * @author: yao
 */
@Slf4j
public class Bean1 {

    private static final Logger log = LoggerFactory.getLogger(Bean1.class);
    private Bean2 bean2;

    @Autowired
    public void setBean2(Bean2 bean2) {
        log.debug("@Autowired生效:{}", bean2);
        this.bean2 = bean2;
    }

    private Bean3 bean3;

    @Resource
    public void setBean3(Bean3 bean3) {
        log.debug("@Resource生效:{}", bean3);
        this.bean3 = bean3;
    }

    private String javaHome;

    @Autowired
    public void setJavaHome(@Value("${JAVA_HOME}") String javaHome) {
        log.debug("@Value 生效");
        this.javaHome = javaHome;
    }

    @PostConstruct
    public void init() {
        log.debug("@PostConstruct 生效");
    }

    @PreDestroy
    public void destroy() {
        log.debug("@PreDestroy生效");
    }

    @Override
    public String toString() {
        return "Bean1{" +
                "bean2=" + bean2 +
                ", bean3=" + bean3 +
                ", javaHome='" + javaHome + '\'' +
                '}';
    }
}
