package com.yao.springtest.blbl.hm.ch15;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @date: 2023-11-30
 * @author: yao
 */
public class A15 {
    public static void main(String[] args) {
        Proxy proxy = new Proxy();
        Target target = new Target();
        proxy.setInterceptor(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
//                return null;
                System.out.println("before");
                return methodProxy.invoke(target, objects);
            }
        });
        proxy.save();
        proxy.save(1);
        proxy.save(2L);
    }
}
