package com.xiao.server.handler;

import com.xiao.message.LoginRequestMessage;
import com.xiao.message.LoginResponseMessage;
import com.xiao.message.Message;
import com.xiao.server.session.SessionFactory;
import com.xiao.service.UserService;
import com.xiao.service.impl.UserServiceMemoryImpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import sun.nio.cs.US_ASCII;

/**
 * @author lao xiao
 * @create 2022年05月07日 08:09:00
 */
@Slf4j
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestMessage msg) throws Exception {
        log.debug("{} login...", msg);
        UserService userService = new UserServiceMemoryImpl();
        Boolean login = userService.login(msg.getUsername(), msg.getPassword());
        LoginResponseMessage responseMessage;
        if(login) {
            responseMessage = new LoginResponseMessage(Boolean.TRUE, null);
            //登陆成功存储会话
            SessionFactory.getSession().bind(ctx.channel(), msg.getUsername());
        } else {
            responseMessage = new LoginResponseMessage(Boolean.TRUE, "登录失败");
        }
        ctx.writeAndFlush(responseMessage);
    }
}
