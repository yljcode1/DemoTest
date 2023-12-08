package com.yao.springtest.blbl.hm.jvm;

/**
 * @date: 2023-12-07
 * @author: yao
 */
public class ArrayInit {
    public static void main(String[] args) {
        TestA[] arrayInits = new TestA[10];
    }

    static class TestA {
        static {
            System.out.println("hello");
        }
    }

}
