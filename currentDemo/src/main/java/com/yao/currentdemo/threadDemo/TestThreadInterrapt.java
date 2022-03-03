package com.yao.currentdemo.threadDemo;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程中断
 */
@Slf4j(topic = "c")
public class TestThreadInterrapt {
    public static void main(String[] args) {
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    log.info("catch exception");
                    e.printStackTrace();
                }
                log.info("running t1");
            }
        };
        t1.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("interrupt");
        t1.interrupt();
    }
}
