package com.yao.currentdemo.threadDemo;

import lombok.extern.slf4j.Slf4j;

/**
 * 观察两个for循环线程交替运行
 */
@Slf4j(topic = "c")
public class TreadTest01 {
    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                log.info("running t1");
            }
        }, "t1").start();

        new Thread(() -> {
            while (true) {
                log.info("running t2");
            }
        }, "t2").start();
    }
}
