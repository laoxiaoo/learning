package com.xiao.server;

import com.xiao.handler.BaseResponseHandler;
import com.xiao.handler.CommonChatHandler;
import com.xiao.handler.LoginRequestHandler;
import com.xiao.handler.WebSocketFrameHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author lao xiao
 * @date 2022年09月30日 16:47
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        //因为基于http协议 使用http的编码器和解码器
        pipeline.addLast(new IdleStateHandler(10, 0, 0, TimeUnit.MINUTES))
                .addLast("HttpServerCodec",new HttpServerCodec())
                //以块方式写 添加ChunkedWriteHandler处理器
                .addLast("ChunkedWriteHandler", new ChunkedWriteHandler())
                //http传输过程中会分段 HttpObjectAggregator可以将多个段聚合
                //这就是为什么，当浏览器发送大量数据时，就会发多次http请求
                .addLast("HttpObjectAggregator",new HttpObjectAggregator(8192))
                //对应websocket 它的数据是以帧(WebSocketFrame)形式传递
                //浏览器请求时ws://localhost:7000/hello表示请求的uri
                //WebSocketServerProtocolHandler的核心功能是将http协议升级为ws协议 并保持长连接
                //是通过一个状态码 101
                .addLast("WebSocketServerProtocolHandler",new WebSocketServerProtocolHandler("/ws"))
                //自定义的handler做业务逻辑处理
                .addLast(new WebSocketFrameHandler())
                .addLast(new BaseResponseHandler())
                .addLast(new LoginRequestHandler())
                .addLast(new CommonChatHandler());
    }
}
