package com.xiao.nio.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * @author lao xiao
 * @version 1.0.0
 * @ClassName NettyClient.java
 * @Description TODO
 * @createTime 2020年09月03日 09:46:00
 */
@Slf4j
public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup loopGroup = new NioEventLoopGroup();
        try {

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(loopGroup)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 500)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new NettyClientHandler());
                        }
                    });
            ChannelFuture future = bootstrap.connect("127.0.0.1", 80);
            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    Channel channel = future.channel();
                    log.debug("channel：{}", channel);
                    channel.writeAndFlush("我是监听发过来的");
                }
            });
            Channel channel = future.sync().channel();
            new Thread(() -> {
                Scanner scanner = new Scanner(System.in);
                String next = scanner.next();
                if("q".equals(next)) {
                    channel.close();
                }
            }).start();
            ChannelFuture channelFuture = channel.closeFuture();
            channelFuture.addListener(closeFuture -> {
                log.debug("channel 已关闭..");
            });
        } finally {
            //loopGroup.shutdownGracefully();
        }
    }
}
