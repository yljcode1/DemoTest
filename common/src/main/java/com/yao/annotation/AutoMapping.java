package com.yao.annotation;

import java.lang.annotation.*;

/**
 * 自动映射注解，标记该注解方法会自动映射结果集
 *
 * @date: 2022/6/21
 * @author: yao
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AutoMapping {
}
