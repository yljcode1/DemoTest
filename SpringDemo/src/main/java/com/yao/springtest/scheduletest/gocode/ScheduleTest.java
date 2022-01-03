package com.yao.springtest.scheduletest.gocode;

import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 进入@Schedule源码测试
 *
 * @author xiao.K
 * @date 2021/10/27
 */
@Slf4j
@Component
public class ScheduleTest {

//    @Scheduled(cron = "*/5 * * * * ?")
//    private void hello() {
//        System.out.println("hello");
//    }

    @Autowired
    Task01 task01;

    @Autowired
    ApplicationContext applicationContext;

    @PostConstruct
    public void test() {
        ScheduleTest bean = applicationContext.getBean(ScheduleTest.class);
        bean.doAsyncMethod();
        bean.doAsyncMethod();
        task01.doAsyncMethod();
        task01.doAsyncMethod();
        log.info("主线程结束");
    }

    @Async
    public void doAsyncMethod() {
        try {
            Thread.sleep(2000);
            log.info("异步方法执行。。。。。。。");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Async
    public void test01() {
        System.out.println(" test01 当前线程" + Thread.currentThread());
    }

    @Async
    public void test02() {
        System.out.println(" test02 当前线程" + Thread.currentThread());
    }
}
