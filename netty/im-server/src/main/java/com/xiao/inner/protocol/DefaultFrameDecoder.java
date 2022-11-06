package com.xiao.inner.protocol;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @author lao xiao
 * @create 2022年05月07日 07:39:00
 */
public class DefaultFrameDecoder extends LengthFieldBasedFrameDecoder {

    public  DefaultFrameDecoder() {
        super(1024, 12,4,0,0);
    }

    public DefaultFrameDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip, boolean failFast) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip, failFast);
    }
}
