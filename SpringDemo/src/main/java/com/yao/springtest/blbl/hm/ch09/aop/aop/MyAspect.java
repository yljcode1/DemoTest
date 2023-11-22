package com.yao.springtest.blbl.hm.ch09.aop.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @date: 2023-11-22
 * @author: yao
 */
@Slf4j
@Aspect
//@Component
public class MyAspect {
    @Before("execution(* com.yao.springtest.blbl.hm.ch09.aop.service.MyService.foo())")
    public void before() {
        log.info("before()");
    }
}
