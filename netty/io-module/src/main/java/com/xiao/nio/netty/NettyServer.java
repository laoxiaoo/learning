package com.xiao.nio.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author lao xiao
 * @version 1.0.0
 * @ClassName NettyServer.java
 * @Description TODO
 * @createTime 2020年09月03日 08:48:00
 */
public class NettyServer {

    public static void main(String[] args) throws InterruptedException {
        //管理连接
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();

        //服务器启动对象
        ServerBootstrap bootstrap = new ServerBootstrap();
        //设置两个线程组
        bootstrap.group(bossGroup, workGroup)
                //选择通道类型
                .channel(NioServerSocketChannel.class)
                //设置线程队列得到的连接数
                .option(ChannelOption.SO_BACKLOG, 128)
                //保持活动连接状态
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                //设置一个处理事情的工作handler
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new NettyServerScheduleHandler()).addLast();
                    }
                });
        //绑定一个端口并且同步
        ChannelFuture sync = bootstrap.bind(80).sync();
        sync.addListener((future) -> {
            if(future.isSuccess()) {
                System.out.println("绑定端口 6666 成功....");
            }
        });
        //对关闭通道进行监听
        sync.channel().closeFuture().sync();
    }
}
