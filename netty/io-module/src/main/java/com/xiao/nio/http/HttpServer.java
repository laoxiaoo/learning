package com.xiao.nio.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author lao xiao
 * @version 1.0.0
 * @ClassName HttpServer.java
 * @Description TODO
 * @createTime 2020年09月16日 08:59:00
 */
public class HttpServer {

    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new HttpServerInitializer());
            ChannelFuture channelFuture = bootstrap.bind(new InetSocketAddress(6666)).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {

        } finally {
            bossGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
