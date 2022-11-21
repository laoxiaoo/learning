package com.xiao.inner.client.handler;

import com.xiao.util.chat.factory.SessionFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * 在连接建立后的 active 事件中
 *
 * @author lao xiao
 * @create 2022年05月07日 07:55:00
 */
@Slf4j
public class InnerClientHandler extends ChannelInboundHandlerAdapter {



    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("断开连接：{}", ctx.toString());
        //删除redis的注册信息
        SessionFactory.getRedisSession().removeChannel(ctx.channel());
        super.channelInactive(ctx);
    }
}
