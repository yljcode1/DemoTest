package com.yao.springtest.beanpostprocesstest.after.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author xiao.K
 * @date 2021/10/27
 */
@Target({ElementType.FIELD,ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RoutingSwitch {
    /**
     * 在配置系统中开关的属性名称，应用系统会实时读取应用系统将会实时读取配置系统中对应开关的值来决定是调用哪个版本
     * @return
     */
    String value() default "";
}
