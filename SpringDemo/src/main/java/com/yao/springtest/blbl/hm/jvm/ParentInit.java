package com.yao.springtest.blbl.hm.jvm;

/**
 * @date: 2023-12-07
 * @author: yao
 */
public class ParentInit extends B {
    public static void main(String[] ags) {
        System.out.println(B.c1);
    }
}

class A {
    public static int c1 = 2;

    static {
        c1 = 3;
    }
}


class B extends A {
    static {
        e = 1;
    }

    public static int e;
    public static int d = c1;

    static {
        c1 = 4;
    }

    static class Parent {
        public static int A = 1;

        static {
            A = 2;
        }
    }

    static class Sub extends Parent {
        public static int B = A;

        static {
            A = 4;
        }
    }

    public static void main(String[] args) {
        System.out.println(Sub.B);
    }
}
