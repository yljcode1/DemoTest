package com.yao.currentdemo.threadDemo;

import javafx.concurrent.Task;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程的方式
 */
@Slf4j
public class TreadTest {
    public static void main(String[] args) {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                log.info("running");
            }
        };
        t1.setName("t1Name");
        t1.start();
    }

    public void t2() {
        Runnable runnable = () -> log.info("running runnable");
        Thread t2 = new Thread(runnable);
        t2.setName("t2");
    }

    public void t3() throws ExecutionException, InterruptedException {
        FutureTask<Integer> t3 = new FutureTask<>(() -> {
            log.info("running futureTask");
            // 从数据库查询到的参数
            return 100;
        });
        Thread tt = new Thread(t3, "t3");
        tt.start();
        log.info("{}", t3.get());
    }
}
