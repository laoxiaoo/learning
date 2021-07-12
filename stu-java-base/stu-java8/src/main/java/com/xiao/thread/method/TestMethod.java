package com.xiao.thread.method;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author xiao ji hao
 * @create 2021年06月18日 16:26:00
 */
@Slf4j
public class TestMethod {

    @Test
    public void testJoin() throws Exception {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("t1 执行结束");
        });

        t1.start();
        t1.join();
        log.debug("main 执行结束");
    }

    @Test
    public void testInterrupt() {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("t1 执行结束");
        });
        t1.start();
        t1.interrupt();
        log.debug("打断标识： {}", t1.isInterrupted());
    }

    @Test
    public void testPark() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            LockSupport.park();
            log.debug("interrupt: {}", Thread.currentThread().isInterrupted());
            LockSupport.park();
            log.debug("interrupt: {}", Thread.interrupted());
            LockSupport.park();
            log.debug("interrupt: {}", Thread.currentThread().isInterrupted());
        });
        t1.start();
        Thread.sleep(1000);
        t1.interrupt();
    }

    @Test
    public void testThread() {
        Thread t1 = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        log.debug("==>main 结束...");
    }

    public static void main(String[] args) throws Exception {
        new TestMethod().testPark();
    }

}
