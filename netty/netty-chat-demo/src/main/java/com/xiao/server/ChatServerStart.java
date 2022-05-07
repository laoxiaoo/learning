package com.xiao.server;

import com.xiao.client.handler.LoginHandler;
import com.xiao.protocol.DefaultFrameDecoder;
import com.xiao.protocol.MessageCodec;
import com.xiao.server.handler.LoginRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author lao xiao
 * @create 2022年05月07日 07:43:00
 */
public class ChatServerStart {

    public static void main(String[] args) throws InterruptedException {
        LoggingHandler LOGGING_HANDLER = new LoggingHandler();
        new ServerBootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new DefaultFrameDecoder());
                        ch.pipeline().addLast(LOGGING_HANDLER);
                        ch.pipeline().addLast(new MessageCodec());
                        ch.pipeline().addLast(new LoginRequestHandler());
                    }
                }).bind(80);
    }

}
