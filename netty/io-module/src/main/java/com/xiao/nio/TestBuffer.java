package com.xiao.nio;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

@Slf4j
public class TestBuffer {
    public static void main(String[] args) {

        System.out.println(ByteBuffer.allocate(5).getClass());
        System.out.println(ByteBuffer.allocateDirect(5).getClass());
        //创建一个buffer，可以存放5个int
        IntBuffer intBuffer = IntBuffer.allocate(5);
        //将i设置进入buffer，将buffer塞满
        for(int i=0; i<intBuffer.capacity(); i++){
            intBuffer.put(i);
        }
        //转化读操作
        intBuffer.flip();
        while (intBuffer.hasRemaining()) {
            log.info("取出数据：{}", intBuffer.get());
        }

    }
}
