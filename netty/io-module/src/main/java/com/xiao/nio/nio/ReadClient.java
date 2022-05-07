package com.xiao.nio.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author xiao ji hao
 * @create 2022年05月01日 14:49:00
 */
@Slf4j
public class ReadClient {

    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress(80));
        int count = 0;
        while (true) {
            ByteBuffer buffer = ByteBuffer.allocate(1024*1024);
            count += sc.read(buffer);
            log.info("收到数据：{}", count);
            buffer.clear();
        }
    }

}
