package com.yao.springtest.beanpostprocesstest.after.controller;

import com.yao.springtest.beanpostprocesstest.after.annotation.RoutingInjected;
import com.yao.springtest.beanpostprocesstest.after.service.HelloService;
import org.springframework.stereotype.Controller;

/**
 * @author xiao.K
 * @date 2021/10/27
 */
@Controller
public class HelloController {
    @RoutingInjected
    private HelloService helloService;

    public void sayHello() {
        this.helloService.sayHello();
    }

    public void sayHi() {
        this.helloService.sayHi();
    }
}
