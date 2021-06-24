package com.xiao.thread.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;

import static com.xiao.thread.lock.TestPark.sleep;

/**
 * @author xiao ji hao
 * @create 2021年06月23日 21:29:00
 */
@Slf4j
public class TestSemaphore {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for(int i=0; i < 30; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    log.debug("==> 开始执行");
                    sleep(1000);
                } finally {
                    semaphore.release();
                }
            }).start();
        }
    }
}
