package com.xiao.netty.channel;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * 测试handler的顺序
 *
 * @author xiao ji hao
 * @create 2021年06月16日 14:41:00
 */
@Slf4j
public class TestHandlerOrder {
    public static void main(String[] args) {
        new ServerBootstrap()
                .group(new NioEventLoopGroup(1), new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast("I1",new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                log.debug("I1");
                                super.channelRead(ctx,msg);
                            }
                        });
                        ch.pipeline().addLast("I2",new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                log.debug("I2");
                                super.channelRead(ctx,msg);
                                ctx.writeAndFlush(ctx.alloc().buffer().writeBytes("server...".getBytes()));
                                ctx.channel().writeAndFlush(ctx.alloc().buffer().writeBytes("server...".getBytes()));
                            }
                        });
                        ch.pipeline().addLast("I2",new SimpleChannelInboundHandler<Student>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, Student msg) throws Exception {

                            }
                        });
                        ch.pipeline().addLast("o3",new ChannelOutboundHandlerAdapter() {
                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                log.debug("o3");
                                super.write(ctx,msg,promise);
                            }
                        });
                        ch.pipeline().addLast("o4",new ChannelOutboundHandlerAdapter() {
                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                log.debug("o4");
                                super.write(ctx,msg,promise);
                            }
                        });
                    }
                }).bind(80);
    }

}
