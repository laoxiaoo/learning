package com.xiao.nio.idleState;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * @program: learning
 * @description: TODO
 * @author: lonely xiao
 * @create: 2020-10-04 18:48
 */
@Slf4j
public class IdleStateServerHandler extends ChannelInboundHandlerAdapter {
    /**
     *
     * @param ctx 上下文
     * @param evt 事件
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        //如果是心跳检测机制
        if(evt instanceof IdleStateEvent ) {
            IdleStateEvent event = (IdleStateEvent) evt;
            String msg = null;
            switch (event.state()) {
                case ALL_IDLE:
                    msg = "读写空闲";
                    break;
                case READER_IDLE:
                    msg = "读空闲";
                    break;
                case WRITER_IDLE:
                    msg = "写空闲";
                    break;
            }
            log.info(msg);
        }
    }
}