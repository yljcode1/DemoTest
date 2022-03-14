package com.yao.currentdemo.threadDemo;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试多线程debug
 */
@Slf4j
public class TestThreadDebug {

    public static void main(String[] args) {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                log.info("thread1 running");
            }
        };
        t1.start();
        Thread.State state = t1.getState();
        log.info("main thread running");
    }

    private static void method1() {

    }
}
