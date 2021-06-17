package com.xiao.netty.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 *  JDK future调试
 * @author Malone Xiao
 * @create 2021年06月16日 13:27:00
 */
@Slf4j
public class TestJdkFuture {

    public static void main(String[] args) throws Exception {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        Future<Integer> future = threadPool.submit(() -> {
            log.debug("进入future中...");
            Thread.sleep(1000);
            return 10;
        });
        log.debug("准备获取结果...");
        Integer integer = future.get();
        log.debug("获取到结果...");
    }

}
