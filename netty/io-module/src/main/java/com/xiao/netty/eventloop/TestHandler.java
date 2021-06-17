package com.xiao.netty.eventloop;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Malone Xiao
 * @create 2021年06月16日 09:05:00
 */
@Slf4j
public class TestHandler {

    public static void main(String[] args) {
        //非IO的线程组
        EventLoopGroup defaultEventLoop = new DefaultEventLoopGroup();
        new ServerBootstrap()
                .group(new NioEventLoopGroup(1), new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast("handler1",new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                log.debug("handler1 进入 : {}", Thread.currentThread().getName());
                                ByteBuf byteBuf = (ByteBuf) msg;
                                log.debug("handler1 消息 : {}", Unpooled.copiedBuffer(byteBuf));
                                //将消息传递给下一个handler
                                ctx.fireChannelRead(msg);
                            }
                        });
                        ch.pipeline().addLast(defaultEventLoop, "handler2", new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                log.debug("handler2 是个很长的业务 {}", Thread.currentThread().getName());
                            }
                        });
                    }
                }).bind(80);
    }

}
