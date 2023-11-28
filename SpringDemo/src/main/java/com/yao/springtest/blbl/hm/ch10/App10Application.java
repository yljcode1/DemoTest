package com.yao.springtest.blbl.hm.ch10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @date: 2023-11-28
 * @author: yao
 */
@SpringBootApplication
public class App10Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(App10Application.class);
        context.close();
    }

    @Bean(initMethod = "init3")
    public Bean1 bean1() {
        return new Bean1();
    }

    @Bean(destroyMethod = "destory3")
    public Bean2 bean2() {
        return new Bean2();
    }
}
