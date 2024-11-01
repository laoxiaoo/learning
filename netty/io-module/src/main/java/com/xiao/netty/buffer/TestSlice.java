package com.xiao.netty.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @author xiao ji hao
 * @create 2021年06月16日 16:46:00
 */
public class TestSlice {

    public static void main(String[] args) {
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer(6);
        buf.writeBytes(new byte[] {'a', 'b', 'c', 'd', 'e', 'f'});
        //采用切片的方式
        ByteBuf buf1 = buf.slice(0, 3);
        //引用计数+1
        buf1.retain();
        ByteBuf buf2 = buf.slice(3, 3);
        //引用计数+2
        buf2.retain();
        buf.release();
        buf1.release();
        buf2.release();
    }

}
