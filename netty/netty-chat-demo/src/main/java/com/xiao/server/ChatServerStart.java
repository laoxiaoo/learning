package com.xiao.server;

import com.xiao.client.handler.LoginHandler;
import com.xiao.protocol.DefaultFrameDecoder;
import com.xiao.protocol.MessageCodec;
import com.xiao.server.handler.ChatRequestHandler;
import com.xiao.server.handler.LoginRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lao xiao
 * @create 2022年05月07日 07:43:00
 */
@Slf4j
public class ChatServerStart {

    public static void main(String[] args) throws InterruptedException {
        LoggingHandler LOGGING_HANDLER = new LoggingHandler();
        //聊天handler
        ChatRequestHandler CHATREQUESTHANDLER = new ChatRequestHandler();
        new ServerBootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new IdleStateHandler(5, 0, 0 ));
                        ch.pipeline().addLast(new ChannelDuplexHandler() {
                            @Override
                            public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                                if(evt instanceof IdleStateEvent) {
                                    IdleStateEvent event = (IdleStateEvent) evt;
                                    if(event.state() == IdleState.READER_IDLE) {
                                        log.debug("已经5s没有读数据了");
                                    }
                                }
                            }
                        });
                        ch.pipeline().addLast(new DefaultFrameDecoder());
                        ch.pipeline().addLast(LOGGING_HANDLER);
                        ch.pipeline().addLast(new MessageCodec());
                        ch.pipeline().addLast(new LoginRequestHandler());
                        ch.pipeline().addLast(CHATREQUESTHANDLER);
                    }
                }).bind(80);
    }

}
