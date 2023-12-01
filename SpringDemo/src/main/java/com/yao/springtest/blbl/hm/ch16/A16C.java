package com.yao.springtest.blbl.hm.ch16;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * 手动模拟切面通知
 *
 * @date: 2023-12-01
 * @author: yao
 */
public class A16C {
    public static void main(String[] args) {
        /*
        两个切面的概念
        aspect =
         通知1(advice) + 切点1(pointcut)
         通知2(advice) + 切点2(pointcut)
         通知3(advice) + 切点3(pointcut)
         ...
         advisor = 更细粒度的切面,包含一个通知和切点
         */
        // 1、备好切点
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* foo())");
        // 2、备好通知
        MethodInterceptor methodInterceptor = methodInvocation -> {
            System.out.println("before");
            Object result = methodInvocation.proceed();//调用目标
            System.out.println("after");
            return result;
        };
        // 3、备好切面
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, methodInterceptor);
        // 4、创建代理
        /*
         a.proxyTargetClass = false,目标实现了接口，用jdk实现
         a.proxyTargetClass = false,目标没有实现接口，用cglib实现
         a.proxyTargetClass = true,总是用cglib实现
         */
        Target1 target = new Target1();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);
        proxyFactory.setInterfaces(target.getClass().getInterfaces());
        I1 proxy = (I1) proxyFactory.getProxy();
        System.out.println(proxy.getClass());
        proxy.foo();
        proxy.bar();

    }

    interface I1 {
        void foo();

        void bar();
    }

    static class Target1 implements I1 {
        @Override
        public void foo() {
            System.out.println("foo()");
        }

        @Override
        public void bar() {
            System.out.println("bar()");
        }
    }
}
