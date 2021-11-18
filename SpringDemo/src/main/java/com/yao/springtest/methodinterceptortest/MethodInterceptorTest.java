package com.yao.springtest.methodinterceptortest;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author xiaoK
 * @date 2021/11/18
 */
public class MethodInterceptorTest implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println(methodInvocation.getMethod().getName());
        return methodInvocation.proceed();
    }
}
