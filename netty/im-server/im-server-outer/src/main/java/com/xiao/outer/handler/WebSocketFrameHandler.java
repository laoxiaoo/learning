package com.xiao.outer.handler;

import com.xiao.entity.BaseRequest;
import com.xiao.outer.code.RequestCodeFactory;
import com.xiao.util.chat.factory.Session;
import com.xiao.util.chat.factory.SpringFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * @author lao xiao
 * @date 2022年09月30日 17:47
 */
public class WebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        System.out.println(textWebSocketFrame.text());
        BaseRequest request = RequestCodeFactory.getMessageClass(textWebSocketFrame.text());
        super.channelRead(channelHandlerContext, request);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerAdded 被调用 "+ctx.channel().id().asLongText());
    }
}
