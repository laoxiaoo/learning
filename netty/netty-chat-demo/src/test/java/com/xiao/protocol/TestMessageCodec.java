package com.xiao.protocol;

import com.xiao.message.LoginRequestMessage;
import com.xiao.protocol.MessageCodec;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LoggingHandler;
import org.junit.Test;

/**
 * @author lao xiao
 * @create 2022年05月05日 21:58:00
 */
public class TestMessageCodec {

    @Test
    public void test() throws Exception {

        //LengthFieldBasedFrameDecoder使用解析
        //1024表示传输的最大字节，12 表示MessageCodec编码是 魔术到内容长度之间的长度，即偏移12后开始读取字节长度
        //4表示长度字段，int=4个字节
        //后面两个表示读取的数据完全读取，不需要截取

        EmbeddedChannel channel = new EmbeddedChannel(
                //防止黏包半包
                new LengthFieldBasedFrameDecoder(1024, 12,4,0,0),
                new LoggingHandler(),
                new MessageCodec()
        );
        //测试编码
        LoginRequestMessage message = new LoginRequestMessage("laoxiao", "123456");
        channel.writeOutbound(message);
        //测试解码
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        new MessageCodec().encode(null, message, buffer);
        channel.writeInbound(buffer);

    }
}
