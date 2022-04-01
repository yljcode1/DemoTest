package com.yao.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaoK
 * @date 2022/4/1
 */
@Slf4j
@RequestMapping("/order")
@RestController
@RequiredArgsConstructor
public class OrderController {
//    public static void main(String[] args) {
//        System.out.println(new BCryptPasswordEncoder().encode("wi1104wi"));
//    }
@RequestMapping("/select")
public String selectById(String id) {
    return id;
}
}
