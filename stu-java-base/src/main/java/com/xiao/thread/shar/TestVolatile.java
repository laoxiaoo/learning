package com.xiao.thread.shar;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiao ji hao
 * @create 2021年06月18日 20:39:00
 */
@Slf4j
public class TestVolatile {
    int count = 0;
    boolean b=false;
    static String LOCK = "lock";

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void testVolatile() throws Exception {
        TestVolatile testVolatile = new TestVolatile();
        Thread t1 = new Thread(() -> {
                for(int i=0; i<100; i++) {
                    synchronized (LOCK) {
                        sleep(10);
                        log.debug("t1: {}", testVolatile.count);
                        testVolatile.count++;
                    }
                }
                log.debug("");
        });
        Thread t2 = new Thread(() -> {
                for(int i=0; i<100; i++) {
                    synchronized (LOCK){
                        sleep(10);
                        log.debug("t2: {}", testVolatile.count);
                        testVolatile.count--;
                    }
                }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.debug("结果：{}", testVolatile.count);
    }

    public static void main(String[] args) throws Exception {
        testVolatile();
    }
}
