package com.yao.springtest.test;

import com.yao.springtest.event.UserRegisterdEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @date: 2023-11-06
 * @author: yao
 */
@Component
public class Component1 {
    @Autowired
    private ApplicationEventPublisher publisher;

    public void register() {
        publisher.publishEvent(new UserRegisterdEvent(this));
    }
}
