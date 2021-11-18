package com.yao.hsqldb.annotation;

import java.lang.annotation.*;

/**
 * @author xiaoK
 * @date 2021/11/18
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Desc {
    String desc() default "";
}
