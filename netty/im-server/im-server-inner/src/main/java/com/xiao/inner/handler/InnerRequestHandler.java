package com.xiao.inner.handler;

import cn.hutool.core.util.StrUtil;
import com.xiao.entity.BaseRequest;
import com.xiao.entity.BaseResponse;
import com.xiao.entity.CommonChatMessage;
import com.xiao.util.chat.factory.SessionFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @author lao xiao
 * @date 2022年10月29日 16:39
 */
@ChannelHandler.Sharable
@Slf4j
public class InnerRequestHandler extends SimpleChannelInboundHandler<CommonChatMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CommonChatMessage msg) throws Exception {
        log.info("内部沟通：{}", msg);
        //查找对方的msg
        Channel channel = SessionFactory.getMemorySession().getChannel(msg.getTo());
        if(Objects.nonNull(channel)) {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setCode(1);
            baseResponse.setMsg(StrUtil.format("{} 对你说：{}", msg.getSource(), msg.getMsg()));
            channel.writeAndFlush(baseResponse);
        } else {
            log.info("{} 不在线...", msg.getTo());
        }
    }


}
