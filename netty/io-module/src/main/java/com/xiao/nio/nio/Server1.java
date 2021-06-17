package com.xiao.nio.nio;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 采用非selector的方式
 *
 * @author Malone Xiao
 * @create 2021年06月15日 11:05:00
 */
@Slf4j
public class Server1 {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(16);
        try (ServerSocketChannel socketChannel = ServerSocketChannel.open();) {
            socketChannel.bind(new InetSocketAddress(80));
            socketChannel.configureBlocking(false);
            while (true) {
                //log.debug("连接中....");
                //连接的过程中是阻塞的，只有有连接了才会进行下一步操作
                SocketChannel channel = socketChannel.accept();
                if(ObjectUtil.isNotNull(channel)) {
                    log.debug("连接完成");
                    //read的过程也是阻塞的
                    int i = channel.read(buffer);
                    if(i>0) {
                        buffer.flip();
                        log.debug("获取到客户端数据: {}", new String(buffer.array()));
                        buffer.clear();
                    }
                }
            }
        } catch (Exception e) {
        }
    }

}
