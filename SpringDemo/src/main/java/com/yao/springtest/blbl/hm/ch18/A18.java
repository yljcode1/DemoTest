package com.yao.springtest.blbl.hm.ch18;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @date: 2023-12-01
 * @author: yao
 */
public class A18 {


    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("aspect1", Aspect1.class);
        context.registerBean("config", Config.class);
        context.registerBean(ConfigurationClassPostProcessor.class);
        // bean后处理器，
        context.registerBean(AnnotationAwareAspectJAutoProxyCreator.class);

        context.refresh();
        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
    }

    static class Target1 {
        public void foo() {
            System.out.println("1 foo");
        }
    }

    static class Target2 {
        public void bar() {
            System.out.println("2 bar");
        }
    }

    @Aspect // 高级切面类
    static class Aspect1 {
        @Before("execution(* foo())")
        public void before() {
            System.out.println("before");
        }

        @After("execution(* foo())")
        public void after() {
            System.out.println("after");
        }
    }

    // 低级切面类
    @Configuration
    static class Config {
        @Bean
        public Advisor advisor3(MethodInterceptor advisor3) {
            AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
            pointcut.setExpression("execution(* foo())");
            return new DefaultPointcutAdvisor(pointcut, advisor3);
        }

        @Bean
        public MethodInterceptor advisor3() {
            return new MethodInterceptor() {
                @Override
                public Object invoke(MethodInvocation methodInvocation) throws Throwable {
                    System.out.println("ad3 before");
                    Object proceed = methodInvocation.proceed();
                    System.out.println("ad3 after");
                    return proceed;
                }
            };
        }
    }
}
