package com.yao.springtest.event;

import org.springframework.context.ApplicationEvent;

/**
 * @date: 2023-11-07
 * @author: yao
 */
public class CommonEvent extends ApplicationEvent {
    public CommonEvent(Object source) {
        super(source);
    }
}
