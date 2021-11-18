package com.yao.springtest.methodinterceptortest;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;

/**
 * @author xiaoK
 * @date 2021/11/18
 */
public class InterceptorConfig {
    public static final String traceExecution ="* com.example.methodinterceptor..*.*(..)";
    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor(){
        MethodInterceptorTest methodInterceptorTest = new MethodInterceptorTest();
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(traceExecution);

        //配置增强类advisor
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(methodInterceptorTest);
        return advisor;
    }
}
