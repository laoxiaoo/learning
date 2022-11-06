package com.xiao.inner.handler;

import cn.hutool.core.util.StrUtil;
import com.xiao.server.entity.CommonChatMessage;
import com.xiao.server.factory.SessionFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lao xiao
 * @date 2022年10月29日 16:39
 */
@ChannelHandler.Sharable
@Slf4j
public class InnerRequestHandler extends SimpleChannelInboundHandler<CommonChatMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CommonChatMessage msg) throws Exception {
        log.info("内部沟通：{}", msg);
        //查找对方的msg
        Channel channel = SessionFactory.getMemorySession().getChannel(msg.getTo());
        channel.writeAndFlush(StrUtil.format("{} 对你说：{}", msg.getSource(), msg.getTo(), msg.getMsg()));
    }
}
