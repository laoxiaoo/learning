package com.xiao.server.handler;

import com.xiao.message.ChatRequestMessage;
import com.xiao.message.ChatResponseMessage;
import com.xiao.server.session.SessionFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Objects;

/**
 * 聊天的消息处理
 *
 * @author lao xiao
 * @create 2022年05月07日 21:47:00
 */
@ChannelHandler.Sharable
public class ChatRequestHandler extends SimpleChannelInboundHandler<ChatRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ChatRequestMessage msg) throws Exception {
        //获取给发给人的channel
        Channel channel = SessionFactory.getSession().getChannel(msg.getTo());
        if(Objects.nonNull(channel)) {
            ChatResponseMessage message = new ChatResponseMessage();
            message.setContent(msg.getContent());
            message.setFrom(SessionFactory.getSession().getUser(ctx.channel()));
            channel.writeAndFlush(message);
        } else {
            ctx.channel().writeAndFlush(new ChatResponseMessage(Boolean.FALSE, "对方不在线"));
        }
    }
}
