package com.yao.springtest.blbl.hm.ch01;

import com.yao.springtest.event.UserRegisterdEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @date: 2023-11-16
 * @author: yao
 */
@Component
public class Component1 {
    @Autowired
    ApplicationContext applicationContext;

    public void sendMessage(ConfigurableApplicationContext context) {
        applicationContext.publishEvent(new UserRegisterdEvent(this));
    }
}
