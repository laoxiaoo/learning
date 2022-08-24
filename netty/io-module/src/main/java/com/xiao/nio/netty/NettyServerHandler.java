package com.xiao.nio.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

/**
 * @author lao xiao
 * @version 1.0.0
 * @ClassName NettyServerHandler.java
 * @Description TODO
 * @createTime 2020年09月03日 09:11:00
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {


    /**
     * 从客户端读取数据
     * @param ctx 上下文，含pipeline，channel, 客户端送的数据
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel channel = ctx.channel();
        ChannelPipeline pipeline = ctx.pipeline();
        System.out.println("客户端地址："+ctx.channel().remoteAddress());
        //msg是一个buf类型
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("客户端发送的消息："+ buf.toString(CharsetUtil.UTF_8));
    }

    /**
     * 读取数据完毕，往客户端发送数据
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //write+flush方法
        //写入缓存，并冲刷
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello : "+ ctx.channel().remoteAddress(), CharsetUtil.UTF_8));
    }
}
