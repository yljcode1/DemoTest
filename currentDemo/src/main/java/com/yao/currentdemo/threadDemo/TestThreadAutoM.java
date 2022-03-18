package com.yao.currentdemo.threadDemo;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class TestThreadAutoM {
    static int i = 0;
    static AtomicLong atomicLong = new AtomicLong(0);

    public static void main(String[] args) throws InterruptedException {
        for (int j = 0; j < 10000; j++) {
            Thread thread = new Thread("t0") {
                @Override
                public void run() {
//                    i++;
                    atomicLong.incrementAndGet();
                }
            };
            thread.start();
            thread.join();
        }
        System.out.println(atomicLong.get());
    }
}
