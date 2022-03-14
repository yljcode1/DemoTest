package com.yao.currentdemo.threadDemo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j(topic = "c")
public class TestTwoParse {
    public static void main(String[] args) {
        TwoParseTermination tpt = new TwoParseTermination();
        tpt.start();
        try {
            TimeUnit.SECONDS.sleep(3500);
            tpt.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

@Slf4j(topic = "c")
class TwoParseTermination {
    /**
     * 监控线程
     */
    private Thread monitor;

    // 启动线程
    public void start() {
        monitor = new Thread(() -> {
            // 可以理解为监控线程，一直监控主线程
            while (true) {
                Thread current = Thread.currentThread();
                // 判断当前线程是否被打断
                if (Thread.currentThread().isInterrupted()) {
                    log.info("料理后世");
                    break;

                }
                try {
                    // 每隔一秒监控一次
                    TimeUnit.SECONDS.sleep(1000); // 情况1
                    log.info("执行监控记录"); // 情况2
                } catch (InterruptedException e) {
                    // sleep出现异常之后，会清楚打断标记
                    // 需要重置打断标记
                    e.printStackTrace();
                    current.interrupt();
                }
            }
        });
        monitor.start();
    }

    // 停止监控线程
    public void stop() {
        monitor.interrupt();
    }
}
