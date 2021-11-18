package com.yao.hsqldb.annotation;

import org.apache.ibatis.annotations.ConstructorArgs;

import java.lang.annotation.*;

/**
 * @author xiaoK
 * @date 2021/11/18
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Add {
 String add() default "";
}
