package com.yao.springtest.beanpostprocesstest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @date: 2023-11-16
 * @author: yao
 */
public class BeanFactoryPostProcessTest {
}

@Configuration
class Config {
    A a;

    B b;

    @Autowired
    public void setA(A a) {
        this.a = a;
    }

    @Autowired
    public void setB(B b) {
        this.b = b;
    }
}

class A {

}

class B {

}
