package com.yao.currentdemo.threadDemo;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "C")
public class TestDebug {
    public static void main(String[] args) {
        method01(10);
    }

    private static void method01(int i) {
        i = i + 1;
        Object o = method02();
        log.info("run method01");
    }

    private static Object method02() {
        Object o = new Object();
        return o;
    }
}
