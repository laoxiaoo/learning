package com.xiao.thread.method;

import lombok.extern.slf4j.Slf4j;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * @author xiao ji hao
 * @create 2021年06月18日 17:15:00
 */
@Slf4j
public class TestStopThread {
    private static Thread monitor;

    public static void main(String[] args) throws InterruptedException {
        start();
        TimeUnit.SECONDS.sleep(10);
        stop();
    }

    public static void start() {
        monitor = new Thread(() -> {
            while (true) {
                Thread thread = Thread.currentThread();
                if(thread.isInterrupted()) {
                    log.debug("==>处理一些事情后退出线程...");
                    break;
                }
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    thread.interrupt();
                }

                log.debug("==>正在执行监控记录...");
            }
        });
        monitor.start();
    }

    public static void stop() {
        monitor.interrupt();
    }

}
