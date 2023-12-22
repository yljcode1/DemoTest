package com.yao.springtest.blbl.hm.jvm;

/**
 * @date: 2023-12-18
 * @author: yao
 */
//public class SoftReferenceDemo2 {
//    public static void main(String[] args) {
//        byte[] bytes = new byte[1024 * 1024 * 100];
//        SoftReference<byte[]> softReference = new SoftReference<>(bytes);
//        bytes = null;
//        System.out.println(softReference.get());
//        byte[] bytes2 = new byte[1024 * 1024 * 100];
//        System.out.println(softReference.get());
//
//        byte[] bytes3 = new byte[1024 * 1024 * 100];
//        softReference = null;
//        System.out.println(softReference.get());
//    }
//}

//public class Singleton {
//    private static Singleton singleton;
//
//    private Singleton() {
//
//    }
//
//    public static Singleton getInstance() {
//        if (singleton == null) {
//            synchronized (Singleton.class) {
//                if (singleton == null) {
//                    return new Singleton();
//                }
//            }
//        }
//        return singleton;
//    }
//}
