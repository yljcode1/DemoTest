package com.yao.springtest.blbl.hm.ch05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Spring生命周期
 *
 * @date: 2023-11-17
 * @author: yao
 */
@SpringBootApplication
public class SpringLiveCircleTest {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringLiveCircleTest.class);
        context.close();
    }
}
