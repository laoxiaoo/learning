package com.xiao.thread.start;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author xiao ji hao
 * @create 2021年06月18日 11:21:00
 */
@Slf4j
public class TestRunnable {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                log.debug("线程运行中....");
                try {
                    //Thread.sleep(2000l);
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t1 = new Thread(runnable);
        t1.start();
        log.debug("主线程运行中.....");
        Thread.sleep(1000l);
        log.debug("t1线程状态： {} ", t1.getState());
        t1.interrupt();
    }

}
