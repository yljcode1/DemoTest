package com.yao.springtest.blbl.hm.ch13;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

/**
 * @date: 2023-11-30
 * @author: yao
 */
public class CglibProxyDemo {
    static class Target {
        public void foo() {
            System.out.println("target foo");
        }
    }

    public static void main(String[] args) {
        Target target = new Target();
        Target proxy = (Target) Enhancer.create(Target.class, (MethodInterceptor) (o, method, objects, methodProxy) -> {
            System.out.println("before");
            Object result = method.invoke(target, objects);// 用方法反射调用目标
//            Object result = methodProxy.invoke(target, objects); 内部没有用反射，需要目标 (Spring)
//            Object target = methodProxy.invoke(o, objects);内部没用有反射，需要代理对象
            System.out.println("after");
            return result;
        });
        proxy.foo();
    }
}
