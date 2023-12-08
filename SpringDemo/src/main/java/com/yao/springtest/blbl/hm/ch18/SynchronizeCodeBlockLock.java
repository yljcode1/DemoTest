package com.yao.springtest.blbl.hm.ch18;

/**
 * @date: 2023-12-05
 * @author: yao
 */
public class SynchronizeCodeBlockLock implements Runnable {
    public static SynchronizeCodeBlockLock lock = new SynchronizeCodeBlockLock();
    public static int count = 0;

    @Override
    public void run() {
        runMethod();
    }


    public void runMethod() {
        synchronized (this){
            for (int i = 0; i < 10000; i++) {
                count++;
            }
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(lock);
        Thread thread2 = new Thread(lock);
        thread1.start();
        thread2.start();
        while (thread2.isAlive() || thread1.isAlive()) {

        }
        System.out.println(count);
    }
}
