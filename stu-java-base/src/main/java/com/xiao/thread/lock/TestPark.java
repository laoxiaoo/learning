package com.xiao.thread.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @author xiao ji hao
 * @create 2021年06月21日 11:05:00
 */
@Slf4j
public class TestPark {

    public void method1() {
        LockSupport.park();

        //LockSupport.unpark(暂停的线程);
    }

    public static void method2() {
        Thread t1 = new Thread(() -> {
            sleep(2000);
            log.debug("==>park");
            LockSupport.park();
            log.debug("==>has unpark");
        });
        t1.start();
        sleep(1000);
        log.debug("==>unpark");
        LockSupport.unpark(t1);
    }


    public static void sleep(long time) {
        try {
            Thread.currentThread().sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        method2();
    }
}
