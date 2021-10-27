package com.yao.springtest.beanpostprocesstest.before.service.impl;

import com.yao.springtest.beanpostprocesstest.before.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @author xiao.K
 * @date 2021/10/27
 */
@Service
public class HelloServiceImplV2 implements HelloService {
    @Override
    public void sayHello() {
        System.out.println("hello from V2");
    }

    @Override
    public void sayHi() {
        System.out.println("hi from V2");
    }
}
