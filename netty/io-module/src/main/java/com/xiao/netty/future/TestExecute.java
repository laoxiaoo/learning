package com.xiao.netty.future;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiao ji hao
 * @create 2021年06月17日 19:29:00
 */
@Slf4j
public class TestExecute {

    public static void main(String[] args) {
        EventLoop eventLoop = new NioEventLoopGroup().next();
        eventLoop.execute(() ->{
            log.debug("hello");
        });
    }
}
