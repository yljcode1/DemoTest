package com.yao.springtest.blbl.hm.ch11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Spring的scope
 * singleton,prototype,request,session,application
 *
 * @date: 2023-11-28
 * @author: yao
 */
@SpringBootApplication
public class A11Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(A11Application.class, args);
    }
}
