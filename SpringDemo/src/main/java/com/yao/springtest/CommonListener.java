package com.yao.springtest;

import com.yao.springdemo.event.CommonEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @date: 2023-11-07
 * @author: yao
 */
@Component
public class CommonListener {
    @EventListener
    @Async
    public void sendMessage(CommonEvent event) {
        System.out.println("============发送短信,可以异步处理============");
    }
}
