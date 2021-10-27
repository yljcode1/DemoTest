package com.yao.springtest.beanpostprocesstest.after.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author xiao.K
 * @date 2021/10/27
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RoutingInjected {
}
