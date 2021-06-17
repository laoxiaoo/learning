package com.xiao.netty.future;

import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.EventLoop;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.Promise;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiao ji hao
 * @create 2021年06月16日 14:24:00
 */
@Slf4j
public class TestPromise {

    public static void main(String[] args) throws Exception {
        EventLoop eventLoop = new DefaultEventLoopGroup().next();
        Promise<Integer> promise = new DefaultPromise<>(eventLoop);
        new Thread(() -> {
            log.debug("开始计算结果设置数据进入promise");
            promise.setSuccess(100);
        }).start();
        log.debug("获取结果中....");
        log.debug("获取到结果：{} ...", promise.get());
    }

}
