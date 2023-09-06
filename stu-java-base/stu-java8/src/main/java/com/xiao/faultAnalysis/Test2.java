package com.xiao.faultAnalysis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class TestTask implements Runnable {
    private Object obj1;
    private Object obj2;
    private int order;

    public TestTask(int order, Object obj1, Object obj2) {
        this.order = order;
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    public void test1() throws InterruptedException {
        synchronized (obj1) {
            //建议线程调取器切换到其它线程运行
            Thread.yield();
            synchronized (obj2) {
                System.out.println("test。。。");
            }

        }
    }
    public void test2() throws InterruptedException {
        synchronized (obj2) {
            Thread.yield();
            synchronized (obj1) {
                System.out.println("test。。。");
            }

        }
    }

    @Override
    public void run() {

        while (true) {
            try {
                if(this.order == 1){
                    this.test1();
                }else{
                    this.test2();
                }
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

public class Test2 {

    public static void main(String[] args) throws InterruptedException {
        Object obj1 = new Object();
        Object obj2 = new Object();

        ExecutorService ex = Executors.newFixedThreadPool(10);
        // 起10个线程
        for (int i = 0; i < 10; i++) {
            int order = i%2==0 ? 1 : 0;
            ex.execute(new TestTask(order, obj1, obj2));
        }

    }
}