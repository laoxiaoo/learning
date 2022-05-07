package com.xiao.netty.http;

import cn.hutool.http.HttpStatus;
import com.sun.org.apache.regexp.internal.RE;
import com.xiao.nio.http.HttpServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * 一个简单的http服务
 * @author lao xiao
 * @create 2022年05月04日 21:38:00
 */
@Slf4j
public class HttpServer {

    public static void main(String[] args) {
        new ServerBootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LoggingHandler());
                        ch.pipeline().addLast(new HttpServerCodec());
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                //会收到两个
                                //请求行：class io.netty.handler.codec.http.DefaultHttpRequest
                                //请求体：class io.netty.handler.codec.http.LastHttpContent$1
                                log.debug("收到客户端请求：{}", msg.getClass());
                                if(msg instanceof DefaultHttpRequest) {
                                    DefaultHttpRequest request = (DefaultHttpRequest) msg;
                                    log.debug(request.uri());
                                    DefaultFullHttpResponse response
                                            = new DefaultFullHttpResponse(request.protocolVersion(), HttpResponseStatus.OK);
                                    byte[] bytes = "<h1>hello</h1>".getBytes(StandardCharsets.UTF_8);
                                    response.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, bytes.length);
                                    response.content().writeBytes(bytes);
                                    ctx.writeAndFlush(response);
                                }
                            }
                        });
                    }
                }).bind(80);
    }

}
