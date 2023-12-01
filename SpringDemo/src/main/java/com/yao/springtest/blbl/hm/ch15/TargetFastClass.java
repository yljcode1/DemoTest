package com.yao.springtest.blbl.hm.ch15;

import org.springframework.cglib.core.Signature;

/**
 * @date: 2023-12-01
 * @author: yao
 */
public class TargetFastClass {

    static Signature s0 = new Signature("save", "()V");
    static Signature s1 = new Signature("save", "(I)V");
    static Signature s2 = new Signature("save", "(J)V");

    // 获取目标方法的一个编号
    /*
    Target
    save()   0
    save(int) 1
    save(long) 2
    signature 包括方法的名字，参数返回值
     */
    public int getIndex(Signature signature) {
        if (s0.equals(signature)) {
            return 0;
        } else if (s1.equals(signature)) {
            return 1;
        } else if (s2.equals(signature)) {
            return 2;
        }
        return -1;
    }

    public Object invoke(int index, Object target, Object[] args) {
        if (0 == index) {
            // 调用无参
            ((Target) target).save();
            return null;
        } else if (1 == index) {
            ((Target) target).save((int) args[0]);
            return null;
        } else if (2 == index) {
            ((Target) target).save((long) args[0]);
            return null;
        } else {
            throw new RuntimeException("no such method");
        }
    }

    public static void main(String[] args) {
        ProxyFastClass fastClass = new ProxyFastClass();
        int index = fastClass.getIndex(new Signature("save", "()V"));
        System.out.println(index);
        fastClass.invoke(index, new Target(), new Object[0]);
    }
}
