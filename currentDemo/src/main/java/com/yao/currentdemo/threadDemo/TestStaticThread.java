package com.yao.currentdemo.threadDemo;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "C")
public class TestStaticThread {
    static int j = 0;

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                j++;
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                j--;
            }
        }, "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(j);
    }
}
