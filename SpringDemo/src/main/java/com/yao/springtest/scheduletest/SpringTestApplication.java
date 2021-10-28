package com.yao.springtest.scheduletest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/** 启动类
 * @author xiao.K
 * @date 2021/10/27
 */
@SpringBootApplication
@EnableScheduling
public class SpringTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringTestApplication.class,args);
    }
}
