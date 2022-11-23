package com.xiao.inner.handler;

import io.netty.channel.*;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lao xiao
 * @date 2022年11月21日 15:23
 */
@Slf4j
public class InnerExceptionHandler extends ChannelDuplexHandler {


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //super.exceptionCaught(ctx, cause);
        try {
            log.info("发生了异常：{}", cause.getMessage());
        } finally {
            ReferenceCountUtil.release(cause);
        }

    }
}
