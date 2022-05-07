package com.xiao.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @author 肖杰
 * @version 1.0.0
 * @ClassName NIOClient.java
 * @Description TODO
 * @createTime 2020年08月19日 09:00:00
 */
public class NIOClient {
    public static void main(String[] args) throws Exception {
        //打开选择器
        Selector selector = Selector.open();
        //打开套字接通道
        SocketChannel channel = SocketChannel.open();
        //设置非阻塞
        channel.configureBlocking(false);
        //注册通道，设置为链接就绪
        channel.register(selector, SelectionKey.OP_CONNECT);
        //绑定IP，端口
        if(!channel.connect(new InetSocketAddress("127.0.0.1", 80))){
            while (!channel.finishConnect()) {
                System.out.println("客户端还未连接，不会阻塞，可以做其他事");
            }
        }
        ByteBuffer byteBuffer = ByteBuffer.wrap("hello".getBytes());
        channel.write(byteBuffer);
        System.out.println("写入完毕");
        channel.close();
    }
}
