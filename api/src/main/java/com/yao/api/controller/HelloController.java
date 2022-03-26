package com.yao.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试gateway的一个对外的controller
 */
@Slf4j
@RestController
@RequestMapping("/goods")
@RequiredArgsConstructor
public class HelloController {
    @GetMapping("/find")
    public String find() {
        return "hello";
    }
}
