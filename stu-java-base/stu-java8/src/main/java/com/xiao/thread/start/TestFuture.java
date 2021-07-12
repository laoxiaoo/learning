package com.xiao.thread.start;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author xiao ji hao
 * @create 2021年06月18日 11:43:00
 */
@Slf4j
public class TestFuture {
    public static void main(String[] args) throws Exception {
        FutureTask<Integer> futureTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.debug("future执行中.....");
                Thread.sleep(1000);
                return 1000;
            }
        });
        new Thread(futureTask).start();
        log.debug("main 线程执行中.....");
        log.debug("获取到future的结果：{}", futureTask.get());
    }
}
