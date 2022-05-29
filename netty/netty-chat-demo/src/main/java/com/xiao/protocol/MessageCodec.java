package com.xiao.protocol;

import com.xiao.message.LoginRequestMessage;
import com.xiao.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * @author lao xiao
 * @create 2022年05月05日 07:52:00
 */
@Slf4j
public class MessageCodec extends ByteToMessageCodec<Message> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        //定义魔术，标识以
        out.writeBytes(new byte[]{1,2,3,4});
        //定义版本号
        out.writeByte(1);
        //内容序列化的方式 0 表示jdk序列化
        out.writeByte(0);
        //指令类型：表示当前是登录指令还是聊天还是其他
        out.writeByte(msg.getMessageType());
        out.writeInt(msg.getSequenceId());
        //为了对其使用：一般魔术->长度，加起来的字节要2^n
        out.writeByte(0xff);
        //正文
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.writeObject(msg);
        byte[] bytes = bos.toByteArray();
        //长度
        out.writeInt(bytes.length);
        //内容
        out.writeBytes(bytes);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List out) throws Exception {
        //魔术 4个字节（正好一个整形）
        int magic = in.readInt();
        byte version = in.readByte();
        //序列化类型
        byte type = in.readByte();
        byte messageType = in.readByte();
        int sequenceId = in.readInt();
        in.readByte();
        int length = in.readInt();
        //获取message实体内容
        byte[] bytes = new byte[length];
        in.readBytes(bytes,0, length);
        if(type == 0) {
            //jdk序列化类型
            ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
            Message message = (Message) inputStream.readObject();
            //log.debug("反序列化数据：{}", message.toString());
            out.add(message);
        }
    }

    public static void main(String[] args) {
        EmbeddedChannel channel = new EmbeddedChannel(
                new LoggingHandler(),
                new MessageCodec()
        );
        LoginRequestMessage message = new LoginRequestMessage("laoxiao", "123456");
        channel.writeOutbound(message);
    }
}
