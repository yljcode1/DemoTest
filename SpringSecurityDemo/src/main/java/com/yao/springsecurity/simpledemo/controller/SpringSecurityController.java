package com.yao.springsecurity.simpledemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试springSecurity包是否会拦截
 * 访问localhost:8080/hello 会进入login页面
 *
 * @author xiao.K
 * @date 2021/10/28
 */
@RestController
@RequestMapping("/security")
public class SpringSecurityController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/vip1")
    public String vip1() {
        return "vip1";
    }

    @GetMapping("/vip2")
    public String vip2() {
        return "vip2";
    }

    @GetMapping("/vip3")
    public String vip3() {
        return "vip3";
    }
}
