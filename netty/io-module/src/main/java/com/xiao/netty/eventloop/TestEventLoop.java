package com.xiao.netty.eventloop;

import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author Malone Xiao
 * @create 2021年06月15日 23:16:00
 */
@Slf4j
public class TestEventLoop {

    public static void main(String[] args) {
        NioEventLoopGroup executors = new NioEventLoopGroup();
        executors.next().submit(() -> {
            System.out.println(Thread.currentThread().getId());
        });
        executors.next().scheduleAtFixedRate(() -> {
            log.debug("定时任务");
        }, 0, 1, TimeUnit.SECONDS);
    }

}
