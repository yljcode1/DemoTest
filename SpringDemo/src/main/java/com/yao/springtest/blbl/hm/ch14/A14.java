package com.yao.springtest.blbl.hm.ch14;

/**
 * 模拟jdk实现反射
 *
 * @date: 2023-11-30
 * @author: yao
 */
public class A14 {
    interface Foo {
        void foo();
    }

    interface InvocationHandler {
        void invoke();
    }

    static class Target implements Foo {
        @Override
        public void foo() {
            System.out.println("target foo");
        }
    }

    public static void main(String[] args) {
        Foo proxy = new $Proxy0(new InvocationHandler() {
            @Override
            public void invoke() {

            }
        });
        proxy.foo();
    }
}
