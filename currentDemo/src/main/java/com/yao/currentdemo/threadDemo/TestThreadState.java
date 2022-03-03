package com.yao.currentdemo.threadDemo;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试线程的各种状态
 */
@Slf4j(topic = "c")
public class TestThreadState {
    public static void main(String[] args) {
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                log.info("running");
            }
        };
        log.info("线程未调用start方法时的状态{}", t1.getState());
        t1.start();
        log.info("线程调用start方法后的状态{}", t1.getState());
    }
}
