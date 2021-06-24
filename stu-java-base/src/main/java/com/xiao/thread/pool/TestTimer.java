package com.xiao.thread.pool;

import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author xiao ji hao
 * @create 2021年06月23日 10:08:00
 */
@Slf4j
public class TestTimer {

    public static void main(String[] args) {
        method3();
    }

    public static void method1() {
        Timer timer = new Timer();

        TimerTask timerTask1 = new TimerTask() {
            @Override
            public void run() {
                log.debug("延迟执行1......");
            }
        };

        timer.schedule(timerTask1, 1000);
    }


    public static void method2() {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
        pool.schedule(() -> {
            log.debug("pool1 ....");
        }, 1, TimeUnit.SECONDS);
    }

    public static void method3() {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
        pool.scheduleWithFixedDelay(() -> {
            log.debug("pool2 ....");
        }, 1, 2, TimeUnit.SECONDS);
    }


}
