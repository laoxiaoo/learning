package com.xiao.nio.websorket;

import com.xiao.nio.idleState.IdleStateServer;
import com.xiao.nio.idleState.IdleStateServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @program: learning
 * @description: TODO
 * @author: lonely xiao
 * @create: 2020-10-04 19:21
 */
@Slf4j
public class WebSocketServer {
    private int port;

    public WebSocketServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {

            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            //使用http解码和编码
                            pipeline.addLast(new HttpServerCodec());
                            //以块方式进行读写
                            pipeline.addLast(new ChunkedWriteHandler());
                            //http传输是分段的，HttpObjectAggregator多段聚合
                            //所以在http传输中，大量数据会有多个http请求
                            pipeline.addLast(new HttpObjectAggregator(8192));
                            // 浏览器访问 ws://127.0.0.1/hello
                            //WebSocketServerProtocolHandler 将http升级 为 websocket
                            pipeline.addLast(new WebSocketServerProtocolHandler("/hello"));
                            //以文本帧的方式进行处理
                            pipeline.addLast(new TextWebSocketFrameHandler());

                        }
                    });
            log.debug("===>服务器端启动");
            ChannelFuture sync = bootstrap.bind(this.port).sync();
            sync.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws Exception {
        new WebSocketServer(80).run();
    }
}