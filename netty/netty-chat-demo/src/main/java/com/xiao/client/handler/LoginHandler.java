package com.xiao.client.handler;

import com.xiao.message.LoginRequestMessage;
import com.xiao.message.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Scanner;

/**
 * 在连接建立后的 active 事件中，输入登陆的相关数据
 *
 * @author lao xiao
 * @create 2022年05月07日 07:55:00
 */
public class LoginHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Message message = (Message) msg;
        System.out.println(message);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名:");
        String username = scanner.nextLine();
        System.out.println("请输入密码:");
        String password = scanner.nextLine();
        LoginRequestMessage loginRequestMessage = new LoginRequestMessage(username, password);
        ctx.writeAndFlush(loginRequestMessage);
    }
}
