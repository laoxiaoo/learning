package com.xiao.outer.handler;


import com.xiao.entity.CommonChatMessage;
import com.xiao.inner.client.ChatClient;
import com.xiao.util.chat.factory.Session;
import com.xiao.util.chat.factory.SpringFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lao xiao
 * @date 2022年10月28日 17:54
 */
@Slf4j
public class CommonChatHandler  extends SimpleChannelInboundHandler<CommonChatMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CommonChatMessage msg) throws Exception {
        log.info("聊天中：{}", msg);
        Session session = SpringFactory.getBean(Session.class);
        Channel channel = session.getChannel(msg.getTo(), (ip, port) -> ChatClient.initClient(ip, port, msg));
        channel.writeAndFlush(msg);
    }
}
