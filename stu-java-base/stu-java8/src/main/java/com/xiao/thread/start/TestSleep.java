package com.xiao.thread.start;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author xiao ji hao
 * @create 2021年06月18日 16:18:00
 */
@Slf4j
public class TestSleep {

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            TimeUnit.SECONDS.sleep(1);
        }
    }

}
