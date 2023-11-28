package com.yao.springtest.blbl.hm.ch11;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * @date: 2023-11-28
 * @author: yao
 */

@Slf4j
@Scope("application")
@Component
public class BeanForApplication {

    @PreDestroy
    public void destory() {
        log.info("destory");
    }
}
