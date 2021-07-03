package com.xiao.jvm.heap;

/**
 * @author Malone Xiao
 * @create 2021年06月06日 16:53:00
 */
public class TestThreadLock {

    public static void main(String[] args) {
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();

        new Thread(() -> {
            synchronized (s1) {
                try {
                    Thread.sleep(1000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (s2){
                }
            }
        }).start();

        new Thread(() -> {
           synchronized (s2) {
               try {
                   Thread.sleep(1000l);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               synchronized (s1) {

               }
           }
        }).start();

    }

}
