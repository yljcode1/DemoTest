package com.yao.currentdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.locks.ReentrantLock;

@SpringBootTest
class CurrentDemoApplicationTests {
    ReentrantLock lock = new ReentrantLock();

    @Test
    void contextLoads() {
        lock.lock();
        next();
        //dosth
    }

    void next(){
        lock.lock();
    }

    public static void main(String[] args) {
        CurrentDemoApplicationTests a = new CurrentDemoApplicationTests();
        new Thread(() -> a.contextLoads());
        new Thread(() -> a.contextLoads());
    }

}
