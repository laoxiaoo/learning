package com.xiao.handler;

import com.xiao.server.entity.CommonChatMessage;
import com.xiao.server.entity.LoginRequestMessage;
import com.xiao.server.factory.Session;
import com.xiao.server.factory.SpringFactory;
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

    }
}
