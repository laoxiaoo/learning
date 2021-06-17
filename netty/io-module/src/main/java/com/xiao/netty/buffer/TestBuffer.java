package com.xiao.netty.buffer;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * buffer的常用方法示例
 *
 * @author Malone Xiao
 * @create 2021年06月15日 09:16:00
 */
public class TestBuffer {

    @Test
    public void readAndWrite() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        //存入数据，也可以用 channel.read(buffer)
        buffer.put(new byte[] {'a', 'b', 'c', 'd', 'e'});
        buffer.flip();
        buffer.get(new byte[4]);
        //[pos=4 lim=5 cap=10]
        //位置已经读取到了4位置
        System.out.println(buffer);
        //获取坐标1的数据，但是pos不会动
        System.out.println((char)buffer.get(1));
        //读取坐标重置
        buffer.rewind();
        System.out.println((char)buffer.get());

        //标记当前位置
        buffer.mark();
        //中间做N个读取操作后，指针回到mark标记处
        buffer.reset();
    }

    @Test
    public void stringToBuffer() {
        //这些方式都是直接切换到读模式的
        ByteBuffer buffer1 = StandardCharsets.UTF_8.encode("hello");
        ByteBuffer buffer2 = ByteBuffer.wrap("hello".getBytes());
    }

}
