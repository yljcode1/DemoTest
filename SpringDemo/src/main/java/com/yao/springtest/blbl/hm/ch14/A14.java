package com.yao.springtest.blbl.hm.ch14;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
        void invoke(Method foo, Object[] args) throws InvocationTargetException, IllegalAccessException;
    }

    static class Target implements Foo {
        @Override
        public void foo() {
            System.out.println("target foo");
        }
    }

    public static void main(String[] args) throws IOException {
        Target target = new Target();
        Foo proxy = new $Proxy0((foo, objects) -> {
            // 无法指定具体的代理方法
            System.out.println("before");
//                new Target().foo();
            Object result = foo.invoke(target, objects);
            System.out.println("after");

        });
        proxy.foo();
        System.in.read();
    }
}
