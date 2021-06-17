package com.xiao.nio.unpooled;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Test;

import java.util.stream.Stream;

/**
 * @author Malone Xiao
 * @create 2021年06月14日 16:05:00
 */
public class TestUnpooled {


    @Test
    public void test1() {
        //创建一个buff长度为10的对象
        ByteBuf buffer = Unpooled.buffer(10);
        //插入数据
        Stream.iterate(0, i -> i+1)
                .limit(10)
                .forEach(buffer::writeByte);
        byte[] array = buffer.array();
        for(int i=0; i<buffer.capacity(); i++) {
            System.out.println(buffer.getByte(i));
        }
    }

}
