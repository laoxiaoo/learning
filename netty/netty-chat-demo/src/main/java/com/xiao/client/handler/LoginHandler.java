package com.xiao.client.handler;

import com.xiao.message.*;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

/**
 * 在连接建立后的 active 事件中，输入登陆的相关数据
 *
 * @author lao xiao
 * @create 2022年05月07日 07:55:00
 */
@Slf4j
public class LoginHandler extends ChannelInboundHandlerAdapter {

    private static CountDownLatch LOGIN_CONT_DOWN_LATCH = new CountDownLatch(1);

    private static volatile Boolean is_login = Boolean.FALSE;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Message message = (Message) msg;
        if(message instanceof LoginResponseMessage) {
            System.out.println(message);
            LoginResponseMessage loginResponseMessage = (LoginResponseMessage) msg;
            if(loginResponseMessage.getSuccess()) {
                //登陆成功，全局设置已经成功
                is_login = Boolean.TRUE;
            }
            LOGIN_CONT_DOWN_LATCH.countDown();
        } else if (message instanceof ChatResponseMessage){
            ChatResponseMessage chatResponseMessage = (ChatResponseMessage) msg;
            log.debug("{} 对你说： {}", chatResponseMessage.getFrom(), chatResponseMessage.getContent());
        }

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //新启动一个线程，为了避免active阻塞影响其他线程
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名:");
        String username = scanner.nextLine();
        System.out.println("请输入密码:");
        String password = scanner.nextLine();
        LoginRequestMessage loginRequestMessage = new LoginRequestMessage(username, password);
        ctx.writeAndFlush(loginRequestMessage);
        System.out.println("等待过程中....");
        new Thread(() -> {
            try {
                LOGIN_CONT_DOWN_LATCH.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!is_login) {
                //登录失败，关闭当前channel
                ctx.channel().close();
                return;
            }
            while(true) {
                System.out.println("==================================");
                System.out.println("send [username] [content]");
                System.out.println("gsend [group name] [content]");
                System.out.println("gcreate [group name] [m1,m2,m3...]");
                System.out.println("gmembers [group name]");
                System.out.println("grouplist");
                System.out.println("gjoin [group name]");
                System.out.println("gquit [group name]");
                System.out.println("quit");
                System.out.println("==================================");
                String command = null;
                try {
                    command = scanner.nextLine();
                } catch (Exception e) {
                    break;
                }

                String[] s = command.split(" ");
                switch (s[0]) {
                    case "send":
                        ctx.writeAndFlush(new ChatRequestMessage(username, s[1], s[2]));
                        break;
                    case "quit":
                        ctx.channel().close();
                        return;
                }
            }
        }).start();
    }
}
