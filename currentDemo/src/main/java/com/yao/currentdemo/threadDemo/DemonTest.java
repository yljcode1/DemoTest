package com.yao.currentdemo.threadDemo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 只要有java线程在运行，java线程就不会停止
 */
@Slf4j
public class DemonTest {
    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                log.info("不是主线程");
            }
        }, "t1").start();
        try {
            TimeUnit.SECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("主线程");
    }
}
