package com.xiao.thread.aqs;

import java.util.concurrent.CyclicBarrier;

/**
 * @author xiao ji hao
 * @create 2021年07月13日 13:36:00
 */
public class TestCyclicBarrier {


    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            public void run() {
                System.out.println("召唤神龙");
            }
        };
        //当达到3时，进入阻塞线程
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(3, runnable);
        for(int i=1; i<=7; i++){
            final int tmp = i;
            new Thread(new Runnable() {
                public void run() {
                    System.out.println("第"+tmp);
                    try{
                        //使cyclicBarrier+1
                        cyclicBarrier.await();
                        System.out.println("执行："+ tmp);
                    }catch(Exception e){
                    }
                }
            }).start();
        }
    }

}
