package com.xiao.handler;

import cn.hutool.core.util.StrUtil;
import com.xiao.server.entity.LoginRequestMessage;
import com.xiao.server.entity.LoginResponseMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lao xiao
 * @create 2022年05月07日 08:09:00
 */
@Slf4j
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestMessage msg) throws Exception {
        log.info("{} login...", msg);
        LoginResponseMessage responseMessage = new LoginResponseMessage(200, StrUtil.format("{}-登录成功", msg.getUsername()));
        ctx.writeAndFlush(responseMessage);
    }
}
