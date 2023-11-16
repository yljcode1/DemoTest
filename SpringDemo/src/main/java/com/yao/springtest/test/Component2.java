package com.yao.springtest.test;

import com.yao.springtest.event.UserRegisterdEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @date: 2023-11-06
 * @author: yao
 */
@Component
public class Component2 {

    @EventListener
    public void listener(UserRegisterdEvent event){
        System.out.println("这个是事件监听");
    }
}
