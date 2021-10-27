package com.yao.springtest.scheduletest.gocode;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author xiao.K
 * @date 2021/10/27
 */
@Component
public class ScheduleTest {

    @Scheduled(cron = "*/5 * * * * ?")
    private void hello() {
        System.out.println("hello");
    }
}
