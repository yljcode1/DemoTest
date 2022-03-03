package com.yao.currentdemo.threadDemo;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c")
public class TestRunAndStart {
    public static void main(String[] args) {
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                log.info("running");
            }
        };
        log.info("{}", t1.getState());
        // 主线程执行run方法
        t1.run();
        // t1线程开启，run方法就绪，run方法由t1线程执行
        t1.start();
    }

}
