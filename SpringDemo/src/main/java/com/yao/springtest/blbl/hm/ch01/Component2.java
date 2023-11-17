package com.yao.springtest.blbl.hm.ch01;

import com.yao.springtest.event.UserRegisterdEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @date: 2023-11-16
 * @author: yao
 */
@Component
public class Component2 {

    @EventListener
    public void aaa(UserRegisterdEvent context){
        System.out.println("aaaa收到了");
    }
}
