package com.yao.springtest.beanpostprocesstest.after.service;

import com.yao.springtest.beanpostprocesstest.after.annotation.RoutingSwitch;
import org.springframework.stereotype.Service;

/**
 * @author xiao.K
 * @date 2021/10/27
 */
@RoutingSwitch("hello.switch")
public interface HelloService {

    @RoutingSwitch("A")
    void sayHello();
    void sayHi();
}
