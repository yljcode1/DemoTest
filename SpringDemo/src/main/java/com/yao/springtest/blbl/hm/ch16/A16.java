package com.yao.springtest.blbl.hm.ch16;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @date: 2023-12-01
 * @author: yao
 */
public class A16 {
    @Aspect
    static class MyAspect {
        @Pointcut("execution(* foo())")
        public void pointcut() {

        }

        @Before("pointcut()")
        public void before() {
            System.out.println("before");
        }

        @After("pointcut()")
        public void after() {
            System.out.println("after");
        }

    }
}
