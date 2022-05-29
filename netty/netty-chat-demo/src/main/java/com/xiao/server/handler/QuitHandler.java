package com.xiao.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lao xiao
 * @create 2022年05月08日 16:02:00
 */
@Slf4j
public class QuitHandler extends ChannelInboundHandlerAdapter {

    /**
     * 客户端退出
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.debug("退出:");
    }

    /**
     * 发生异常的退出
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.debug("异常退出:");
    }
}
