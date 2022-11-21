package com.xiao.inner.client;

import cn.hutool.core.util.StrUtil;
import com.xiao.entity.BaseRequest;
import com.xiao.inner.client.handler.InnerClientHandler;
import com.xiao.inner.protocol.MessageCodec;
import com.xiao.util.chat.factory.SessionFactory;
import com.xiao.util.chat.factory.SpringFactory;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lao xiao
 * @date 2022年10月28日 19:06
 */
@Slf4j
public class ChatClient extends AbstractChatClient {

    /**
     * 初始化连接
     * @param ip
     * @param port
     */
    public static Channel initClient(String ip, Integer port, Object msg) {
        LoggingHandler LOGGING_HANDLER = new LoggingHandler();
        NioEventLoopGroup group = new NioEventLoopGroup(1);
        ChannelFuture channelFuture = new Bootstrap()
                .group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        //ch.pipeline().addLast(LOGGING_HANDLER);
                        ch.pipeline().addLast(new MessageCodec());
                        ch.pipeline().addLast(new InnerClientHandler());
                    }
                }).connect(ip, port);
        Channel channel = channelFuture.channel();
        channelFuture.addListener(var -> {
            log.info("连接内部服务器:{} {}", ip, port);
            channel.writeAndFlush(msg);
        });
        channelFuture.channel().closeFuture().addListener(close -> {
            group.shutdownGracefully();
        });
        return channelFuture.channel();
    }



}
