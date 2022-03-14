package com.yao.currentdemo.threadDemo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 测试join
 */
@Slf4j(topic = "c")
public class TestJoin {
    static int i = 0;
    static int j = 0;

    public static void main(String[] args) throws InterruptedException {
        test01();
    }

    private static void test01() throws InterruptedException {
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i = 10;
            }
        };
        Thread t2 = new Thread("t2") {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                j = 20;
            }
        };
        t1.start();
        t2.start();
        long start = System.currentTimeMillis();
        log.debug("join begin");
        t1.join();
        log.debug("t1 join end");
        t2.join();
        log.debug("t2 join end");
        long end = System.currentTimeMillis();
        log.debug("r1:{} r2:{} cost:{}", i, j, end - start);
    }
}
