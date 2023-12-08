package com.yao.springtest.blbl.hm.jvm;

/**
 * @date: 2023-12-07
 * @author: yao
 */
public class Test1 {
    public static void main(String[] args) {
        System.out.println("A");
        new Test1();
        new Test1();
    }

    public Test1() {
        System.out.println("B");
    }

    {
        System.out.println("C");
    }

    static {
        System.out.println("D");
    }
}
