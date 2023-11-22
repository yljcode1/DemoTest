package com.yao.springtest.blbl.hm.ch09.aop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @date: 2023-11-22
 * @author: yao
 */
@Slf4j
@Service
public class MyService {
    public void foo() {
        log.info("foo()");
    }
}
