package com.yao.springtest.scheduletest.gocode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Task01 {
    @Async
    public void doAsyncMethod() {
        try {
            Thread.sleep(2000);
            log.info("异步方法执行。。。。。。。");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
