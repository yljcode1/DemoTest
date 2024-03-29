package com.yao.springtest.blbl.hm.ch10;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 * @date: 2023-11-28
 * @author: yao
 */
@Slf4j
public class Bean1 implements InitializingBean {
    @PostConstruct
    public void init1() {
        log.info("初始化1");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("初始化2");
    }

    public void init3() {
        log.info("初始化3");
    }
}
