package com.yao.springsecurity.simpledemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/** 测试springSecurity包是否会拦截
 * 访问localhost:8080/hello 会进入login页面
 * @author xiao.K
 * @date 2021/10/28
 */
@RestController
public class SpringSecurityController {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
