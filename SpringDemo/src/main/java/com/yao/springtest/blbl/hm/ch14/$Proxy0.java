package com.yao.springtest.blbl.hm.ch14;

import com.yao.springtest.blbl.hm.ch14.A14.InvocationHandler;

/**
 * 模仿代理类
 *
 * @date: 2023-11-30
 * @author: yao
 */
public class $Proxy0 implements A14.Foo {

    private InvocationHandler handler;

    // 将实现委托给创建者
    public $Proxy0(InvocationHandler handler) {
        this.handler = handler;
    }

    @Override
    public void foo() {
//        // 1、实现功能的增强
//        System.out.println("before");
//        // 2、调用目标
//        new A14.Target().foo();
        handler.invoke();
    }
}
