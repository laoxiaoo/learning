package com.xiao.nio.websorket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * @program: learning
 * @description: TODO
 * @author: lonely xiao
 * @create: 2020-10-04 20:17
 */
@Slf4j
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        log.info("服务器收到消息：{}", msg.text());
        ctx.channel().writeAndFlush(new TextWebSocketFrame("服务收到消息:"+ LocalDateTime.now()+ " : " + msg.text()));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //LongText是唯一的，ShortText不是唯一的
        log.info("added LongText {}", ctx.channel().id().asLongText());
        log.info("added ShortText {}", ctx.channel().id().asShortText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //LongText是唯一的，ShortText不是唯一的
        log.info("removed LongText {}", ctx.channel().id().asLongText());
        log.info("removed ShortText {}", ctx.channel().id().asShortText());
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("发生异常：{}", cause);
        ctx.close();
    }
}