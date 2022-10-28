package com.xiao.handler;

import com.xiao.server.entity.BaseResponse;
import com.xiao.util.JacksonUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * @author lao xiao
 * @date 2022年10月28日 14:45
 */
public class BaseResponseHandler extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if(msg instanceof BaseResponse) {
            super.write(ctx, new TextWebSocketFrame(JacksonUtil.toJsonString(msg)), promise);
        } else {
            super.write(ctx, msg, promise);
        }
    }
}
