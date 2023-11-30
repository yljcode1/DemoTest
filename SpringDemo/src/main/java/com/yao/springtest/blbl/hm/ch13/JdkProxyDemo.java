package com.yao.springtest.blbl.hm.ch13;

import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * @date: 2023-11-30
 * @author: yao
 */
public class JdkProxyDemo {
    interface Foo {
        void foo();
    }

    static class Target implements Foo {

        @Override
        public void foo() {
            System.out.println("target foo");
        }
    }

    //jdk 只能正对接口代理
    // cglib
    public static void main(String[] args) throws IOException {
        Target target = new Target();
        ClassLoader loader = JdkProxyDemo.class.getClassLoader();
        Foo foo = (Foo) Proxy.newProxyInstance(loader, new Class[]{Foo.class}, (proxy, method, args1) -> {
            System.out.println("before");
            // 目标.方法(参数)
            // 方法.invoke(目标,参数)
            Object result = method.invoke(target, args1);
            System.out.println("after");
            // 返回执行结果
            return result;
//            return null;
        });
        foo.foo();
        System.in.read();
    }
}
