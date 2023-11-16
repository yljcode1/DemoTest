package com.yao.springtest.test;

import com.yao.springtest.event.CommonEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @date: 2023-11-07
 * @author: yao
 */
@Component
public class CommonPublisher {
    @Autowired
    private ApplicationEventPublisher publisher;

    public void publisher() {
        publisher.publishEvent(new CommonEvent(this));
    }

}
