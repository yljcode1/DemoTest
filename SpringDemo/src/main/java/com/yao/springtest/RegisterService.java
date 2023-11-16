package com.yao.springtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * 用于模拟注册用户登录和发送短信解耦的逻辑
 *
 * @date: 2023-11-07
 * @author: yao
 */
@Service
public class RegisterService {

    @Autowired
    private CommonPublisher publisher;
    @Autowired
    private ApplicationContext context;

    /**
     * 注册发送短信逻辑
     */
    public void registerAndSms() {
        // 用户注册逻辑
        System.out.println("===========注册用户成功==============");
        // 给用户发送短信逻辑
        publisher.publisher();
    }
}
