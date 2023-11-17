package com.yao.springtest.blbl.hm.ch05;

import java.util.ArrayList;
import java.util.List;

/**
 * 模版方法测试
 *
 * @date: 2023-11-17
 * @author: yao
 */
public class TestMethodTemplate {
    public static void main(String[] args) {
        People people = new People();
        people.addList(new A() {
            @Override
            void eat() {
                System.out.println("eat");
            }
        });
        people.life();
    }
}

class People {
    public void life() {
//        eat();
        for (A a : list) {
            a.eat();
        }
        sing();
        work();
    }

    private void work() {
    }

    private void sing() {
    }

    List<A> list = new ArrayList<>();

    public void addList(A a) {
        list.add(a);
    }
}

abstract class A {
    void eat() {

    }
}
