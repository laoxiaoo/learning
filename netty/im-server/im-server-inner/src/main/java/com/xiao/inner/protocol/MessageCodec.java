package com.xiao.inner.protocol;



import com.xiao.entity.BaseRequest;
import com.xiao.entity.CommonChatMessage;
import com.xiao.util.JacksonUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
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
public class MessageCodec extends ByteToMessageCodec<BaseRequest> {
    @Override
    protected void encode(ChannelHandlerContext ctx, BaseRequest msg, ByteBuf out) throws Exception {
        //定义魔术，标识以
        out.writeBytes(new byte[]{1,2,3,4});
        //定义版本号
        out.writeByte(1);
        //内容序列化的方式 0 表示jdk序列化
        out.writeByte(0);
        out.writeInt(1);
        //为了对其使用：一般魔术->长度，加起来的字节要2^n
        out.writeByte(0xff);
        //正文
        String json = JacksonUtil.toJsonString(msg);
        byte[] bytes = json.getBytes("utf-8");
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
        //in.readByte();
        int length = in.readInt();
        //获取message实体内容
        byte[] bytes = new byte[length];
        in.readBytes(bytes,0, length);
        if(type == 0) {
            CommonChatMessage message = JacksonUtil.toBean(new String(bytes, "utf-8"), CommonChatMessage.class);
            //log.debug("反序列化数据：{}", message.toString());
            out.add(message);
        }
    }

}
