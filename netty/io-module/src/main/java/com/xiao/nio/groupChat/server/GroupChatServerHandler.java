package com.xiao.nio.groupChat.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

/**
 * @program: learning
 * @description: TODO
 * @author: lonely xiao
 * @create: 2020-10-04 10:20
 */
@Slf4j
public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {
    /**
     * 定义一个channel组，管理所有的channe
     */
    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * channel处于活跃 状态
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.debug(ctx.channel().remoteAddress()+" 上线了");
    }

    /**
     * 处于 不活跃 状态
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.debug(ctx.channel().remoteAddress()+" 下线了");
    }

    /**
     * 一旦建立连接，第一个被执行
     * 将 handler加入 加入到channelHandler中
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channels.writeAndFlush("客户端["+channel.remoteAddress()+"]加入聊天\n");
        channels.add(channel);
        log.debug("channel size : "+channels.size());
    }

    /**
     * 断开连接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channels.writeAndFlush("客户端["+channel.remoteAddress()+"]离开聊天\n");
        log.debug("channel size : "+channels.size());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        channels.forEach(c -> {
            if(c != channel) {
                c.writeAndFlush("[客户]["+channel.remoteAddress()+"]发送了消息:"+msg);
            } else {
                c.writeAndFlush("[自己]["+channel.remoteAddress()+"]发送了消息:"+msg);
            }
        });
    }
}