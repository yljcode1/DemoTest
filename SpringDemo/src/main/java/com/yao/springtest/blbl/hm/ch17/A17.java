package com.yao.springtest.blbl.hm.ch17;

import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;

/**
 * @date: 2023-12-01
 * @author: yao
 */
public class A17 {
    public static void main(String[] args) throws NoSuchMethodException {
        // execution表达式选择
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* foo())");
        System.out.println(pointcut.matches(T1.class.getMethod("foo"), T1.class));
        System.out.println(pointcut.matches(T1.class.getMethod("bar"), T1.class));


        // @annotation形式判断选择
        AspectJExpressionPointcut pointcut1 = new AspectJExpressionPointcut();
        pointcut1.setExpression("@annotation(org.springframework.transaction.annotation.Transactional)");
        System.out.println(pointcut.matches(T1.class.getMethod("foo"), T1.class));
        System.out.println(pointcut.matches(T1.class.getMethod("bar"), T1.class));

        StaticMethodMatcherPointcut pt3 = new StaticMethodMatcherPointcut() {
            @Override
            public boolean matches(Method method, Class<?> aClass) {
                // 检查方法上是否添加了Transactional注解
                MergedAnnotations annotations = MergedAnnotations.from(method);
                if (annotations.isPresent(Transactional.class)) {
                    return true;
                }
                // 查看类上是不是加了
                MergedAnnotations annotations1 = MergedAnnotations.from(aClass, MergedAnnotations.SearchStrategy.TYPE_HIERARCHY);
                if (annotations1.isPresent(Transactional.class)) {
                    return true;
                }
                return false;
            }
        };
    }

    static class T1 {
        @Transactional
        public void foo() {

        }

        public void bar() {

        }
    }

    @Transactional
    static class T2 {
        public void foo() {

        }

        public void bar() {

        }
    }
}
