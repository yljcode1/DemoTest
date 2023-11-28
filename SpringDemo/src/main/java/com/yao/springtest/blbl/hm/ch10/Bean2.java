package com.yao.springtest.blbl.hm.ch10;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;

import javax.annotation.PreDestroy;

/**
 * @date: 2023-11-28
 * @author: yao
 */
@Slf4j
public class Bean2 implements DisposableBean {
    @PreDestroy
    public void destory1() {
        log.info("销毁1");
    }


    @Override
    public void destroy() throws Exception {
        log.info("销毁2");
    }

    public void destory3() {
        log.info("销毁3");
    }
}
