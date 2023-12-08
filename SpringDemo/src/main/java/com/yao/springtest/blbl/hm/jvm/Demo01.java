package com.yao.springtest.blbl.hm.jvm;

/**
 * @date: 2023-12-07
 * @author: yao
 *
 * 通过子类引用父类的静态变量，子类不会初始化
 */
public class Demo01 {
    public static void main(String[] args) {
//        new B02();
        System.out.println(B02.a);
    }
}

class A02 {
    static int a = 0;


    static {
        a = 1;
    }
}

class B02 extends A02 {
    static int b = 3;

    static {
        a = 2;
    }
}
