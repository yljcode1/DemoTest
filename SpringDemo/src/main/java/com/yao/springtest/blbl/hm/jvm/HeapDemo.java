package com.yao.springtest.blbl.hm.jvm;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @date: 2023-12-13
 * @author: yao
 */
public class HeapDemo {
    public static void main(String[] args) throws IOException {
        ArrayList<Object> objects = new ArrayList<>();

        while (true) {
            System.in.read();
            System.out.println("添加一次");
            objects.add(new byte[1024 * 1024 * 1000]);
        }
    }
}
