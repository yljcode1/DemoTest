package com.yao.springtest.blbl.hm.jvm;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;

/**
 * @date: 2023-12-18
 * @author: yao
 */
public class SoftReferenceDemo3 {
    public static void main(String[] args) {
        ArrayList<SoftReference> objects = new ArrayList<>();
        ReferenceQueue<byte[]> queue = new ReferenceQueue<>();
        for (int i = 0; i < 0; i++) {
            byte[] bytes = new byte[1024 * 1024 * 100];
            SoftReference<byte[]> student = new SoftReference<>(bytes, queue);
            objects.add(student);
        }
    }
}
