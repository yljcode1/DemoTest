package com.yao.springtest.blbl.hm.jvm;

import java.io.IOException;

/**
 * 用jclasslib查看常量池
 *
 * @date: 2023-12-05
 * @author: yao
 */
public class ConstPool {
    public static final String a = "我爱中国";
    public static final String b = "我爱中国";
    public static final String ab = "abc";
    public static final String bc = "abc";
    public static final String abc = "abc";

    public static void main(String[] args) throws IOException {
//        ConstPool constPool = new ConstPool();
        // iconst_0 将0放入操作数栈中，istore_1 将i存入局部变量表中下标为1的位置，iload_1 操作数栈load到局部变量表
//
//        int i = 0; i = i++;
        // i的值为1
//        int i = 0; i = ++i;

        int i = 0, j = 0, k = 0;
        i = i++;
        j = j + 1;
        k += 1;
        System.out.println(i);
        System.in.read();
    }
}
