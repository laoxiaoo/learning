package com.xiao.netty.decode;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LoggingHandler;

import java.nio.charset.StandardCharsets;

/**
 * 测试长度字段的解析
 *
 * @author lao xiao
 * @create 2022年05月04日 13:39:00
 */
public class TestLengthFieldDecoder {

    public static void main(String[] args) {
        EmbeddedChannel channel = new EmbeddedChannel(
                //最大长度，长度标识起始index,长度标识长度（int 4个字节）, 从长度字段起，第几个是内容， 剥离的字段长度
                new LengthFieldBasedFrameDecoder(1024, 0,4,1,5),
                new LoggingHandler()
        );
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();
        send(byteBuf, "hello,lao xiao");
        send(byteBuf, "LALALA");
        channel.writeInbound(byteBuf);
    }

    private static void send(ByteBuf byteBuf, String content) {
        byteBuf.writeInt(content.getBytes(StandardCharsets.UTF_8).length);
        byteBuf.writeByte(1);
        byteBuf.writeBytes(content.getBytes(StandardCharsets.UTF_8));
    }

}
