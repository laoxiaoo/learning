package com.xiao.thread.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 测试线程池
 *
 * @author xiao ji hao
 * @create 2021年07月13日 12:18:00
 */
public class TestThreadPool {

    public static void main(String[] args) {
        new ThreadPoolExecutor(1,1, 1000l,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10), runnable -> new Thread(){}, ((r, executor) -> {
        }));
    }

}
