package com.yao.springtest.blbl.hm.ch18;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.stream.IntStream;

/**
 * @date: 2023-12-05
 * @author: yao
 */
public class TestSync {

    public int count = 0;

    public static void main(String[] args) {
        String s = RandomStringUtils.randomAlphabetic(10);
        System.out.println(s);
//        TestSync testSync = new TestSync();
//
//        IntStream.range(1, 8).forEach(i -> {
//            Thread thread = new Thread(() -> {
//                try {
//                    testSync.testSy();
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            });
//            thread.setName(String.valueOf(i));
//            thread.start();
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//
//        });
        TestSync testSync = new TestSync();
        testSync.testAdd();
        System.out.println(testSync.count);
    }

    public void testSy() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName());
    }

    public void testAdd() {
        IntStream.range(1, 3).forEach(i -> {
            new Thread(() ->
            {
                for (int i1 = 0; i1 < 10000; i1++) {
                    count++;
                }
            }).start();
        });
    }
}
