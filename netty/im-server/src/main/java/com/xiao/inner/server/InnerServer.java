package com.xiao.inner.server;

import com.xiao.inner.handler.InnerRequestHandler;
import com.xiao.inner.protocol.DefaultFrameDecoder;
import com.xiao.inner.protocol.MessageCodec;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lao xiao
 * @date 2022年10月29日 16:33
 */
@Slf4j
public class InnerServer {

    public void start(Integer port) {
        InnerRequestHandler CHATREQUESTHANDLER = new InnerRequestHandler();
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
                                        log.info("已经5s没有读数据了");
                                    }
                                }
                            }
                        });
                        ch.pipeline().addLast(new DefaultFrameDecoder());
                        ch.pipeline().addLast(new MessageCodec());
                        ch.pipeline().addLast(CHATREQUESTHANDLER);
                    }
                }).bind(80);
    }

}
