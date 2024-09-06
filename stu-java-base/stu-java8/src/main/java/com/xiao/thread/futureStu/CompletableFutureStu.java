package com.xiao.thread.futureStu;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

/**
 * @author lao xiao
 * @date 2024-07-31 15:05
 */
@Slf4j
public class CompletableFutureStu {

    public static void main(String[] args) throws InterruptedException {
        CompletableFuture.runAsync(() -> log.info("第一个任务"))
                .thenRun(() -> log.info("第二个任务"))
                .thenRunAsync(() -> log.info("第三个任务"));
        Thread.sleep(1000);
        CompletableFuture.supplyAsync(() -> {
            log.info("第一个-有返回值");
            return "supplyAsync-1-res";
        }).thenApply(var -> {
            log.info("接收上一个任务，有返回值：{}",var);
            return "thenApply-2-res";
        }).thenAccept(v -> log.info("接收上一个任务的值，没有返回值：{}", v));
        Thread.sleep(10000);
    }

}
