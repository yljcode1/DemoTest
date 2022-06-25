package com.yao.service.jobhandler;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @date: 2022/5/5
 * @author: yao
 */
@Component
@EnableScheduling
public class SpringScheduleHandler {

    @Scheduled(cron = "*/10 * * * * *")
    public void execute() {
        System.out.println("hello");
    }
}
