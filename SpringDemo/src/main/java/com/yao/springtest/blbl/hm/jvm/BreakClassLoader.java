package com.yao.springtest.blbl.hm.jvm;

/**
 * @date: 2023-12-07
 * @author: yao
 */
public class BreakClassLoader extends ClassLoader {
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
//        return super.loadClass(name);
        byte[] data = loadClassData(name);
        return defineClass(name, data, 0, data.length);
    }

    private byte[] loadClassData(String name) {
        return null;
    }

    public static void main(String[] args) {

    }
}
