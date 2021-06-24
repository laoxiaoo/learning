package com.xiao.thread.start;

import lombok.extern.slf4j.Slf4j;

/**
 * Thread的方式创建线程
 * @author xiao ji hao
 * @create 2021年06月18日 11:15:00
 */
@Slf4j
public class TestThread {
    public static void main(String[] args) {
        new MyThread().start();
        log.debug("主线程运行结束...");
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            log.debug("线程运行中....");
        }
    }
}
