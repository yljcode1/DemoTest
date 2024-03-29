package com.yao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class YaoJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(YaoJobApplication.class, args);
    }

}
