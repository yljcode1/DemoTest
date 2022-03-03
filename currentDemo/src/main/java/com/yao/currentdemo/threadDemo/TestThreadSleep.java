package com.yao.currentdemo.threadDemo;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试线程sleep方法
 */
@Slf4j(topic = "c")
public class TestThreadSleep {

    public static void main(String[] args) {
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("running");
            }
        };
        t1.start();
        try {
            // 主线程阻塞
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("{}线程的状态是{}", t1.getName(), t1.getState());
    }
}
