package com.xiao.client;

import com.xiao.client.handler.LoginHandler;
import com.xiao.protocol.DefaultFrameDecoder;
import com.xiao.protocol.MessageCodec;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author lao xiao
 * @create 2022年05月07日 07:50:00
 */
public class ChatClientStart {

    public static void main(String[] args) throws InterruptedException {
        LoggingHandler LOGGING_HANDLER = new LoggingHandler();
        new Bootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(LOGGING_HANDLER);
                        ch.pipeline().addLast(new MessageCodec());
                        ch.pipeline().addLast(new LoginHandler());
                    }
                }).connect("127.0.0.1", 80).sync().channel();
    }

}
