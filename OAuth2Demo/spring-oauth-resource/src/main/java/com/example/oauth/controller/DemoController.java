package com.example.oauth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiao.K
 * @date 2021/11/4
 */
@RestController
public class DemoController {

    @GetMapping("/info")
    public Authentication authentication(Authentication authentication){
        return authentication;
    }
}
