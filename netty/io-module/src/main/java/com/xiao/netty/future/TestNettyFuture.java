package com.xiao.netty.future;

import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.EventLoop;
import io.netty.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;

/**
 * @author  xiao ji hao
 * @create 2021年06月16日 13:55:00
 */
@Slf4j
public class TestNettyFuture {

    public static void main(String[] args) throws Exception {
        DefaultEventLoopGroup loopGroup = new DefaultEventLoopGroup();
        EventLoop eventLoop = loopGroup.next();
        Future<Integer> future = eventLoop.submit(() -> {
            log.debug("进入future中...");
            Thread.sleep(1000);
            return 10;
        });
        log.debug("准备获取结果...");
        log.debug("获取到结果 {}...", log.getName());
    }

}
