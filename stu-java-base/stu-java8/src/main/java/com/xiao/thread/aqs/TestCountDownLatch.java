package com.xiao.thread.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

import static com.xiao.thread.lock.TestPark.sleep;

/**
 *
 *
 * @author xiao ji hao
 * @create 2021年06月23日 22:01:00
 */
@Slf4j
public class TestCountDownLatch {

    public static void main(String[] args){
        CountDownLatch countDownLatch = new CountDownLatch(3);

        for (int i=1; i<=3; i++) {
            int iTmp = i;
            new Thread(() -> {
                sleep(iTmp*1000);
                countDownLatch.countDown();
                log.debug("==> 一个线程执行");
            }).start();
        }
        try {
            log.debug("==> waiting");
            countDownLatch.await();
            log.debug("==> 等待结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
