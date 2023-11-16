package com.yao.springtest;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

/**
 * @date: 2023-11-07
 * @author: yao
 */
public class SpringStartedListener implements ApplicationListener<ApplicationStartedEvent> {
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        System.out.println("========启动监听,可以尝试去做一些初始化操作，比如解压文件，判断属性=========");
    }
}
