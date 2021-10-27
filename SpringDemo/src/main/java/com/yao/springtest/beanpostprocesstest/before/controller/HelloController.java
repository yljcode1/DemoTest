package com.yao.springtest.beanpostprocesstest.before.controller;

import com.yao.springtest.beanpostprocesstest.before.service.impl.HelloServiceImplV1;
import com.yao.springtest.beanpostprocesstest.before.service.impl.HelloServiceImplV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author xiao.K
 * @date 2021/10/27
 */
@Controller
public class HelloController {
    @Autowired
    private HelloServiceImplV1 helloServiceImplV1;
    @Autowired
    private HelloServiceImplV2 helloServiceImplV2;

    public void sayHello() {
        if (getHelloVersion() == "A") {
            helloServiceImplV1.sayHello();
        } else {
            helloServiceImplV2.sayHello();
        }
    }

    public String getHelloVersion() {
        return "A";
    }

    public void sayHi() {
        if (getHiVersion() == "A") {
            helloServiceImplV1.sayHi();
        } else {
            helloServiceImplV2.sayHi();
        }
    }

    private String getHiVersion() {
        return "A";
    }
}
