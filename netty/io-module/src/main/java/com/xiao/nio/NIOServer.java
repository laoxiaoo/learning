package com.xiao.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author 肖杰
 * @version 1.0.0
 * @ClassName NIOServer.java
 * @Description TODO
 * @createTime 2020年08月18日 08:58:00
 */
@Slf4j
public class NIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        Selector selector = Selector.open();
        //绑定一个服务器监听端口
        socketChannel.bind(new InetSocketAddress(7070));
        //设置为非阻塞
        socketChannel.configureBlocking(false);
        //将selector与channel关联
        //SelectionKey: 之后发生的事件都集中这里
        log.debug("连接channel:{}", socketChannel.hashCode());
        SelectionKey sscKey = socketChannel.register(selector, 0, null);
        //注册一个连接事件
        sscKey.interestOps(SelectionKey.OP_ACCEPT);
        //循环获取连接事件
        while (true) {
            //1s没有获取到事件就重新获取
            if(selector.select(1000) == 0) {
                //System.out.println("没有人连接....");
                continue;
            }
            //获取发生的事件集合
            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
            while (keys.hasNext()) {
                SelectionKey key = keys.next();
                keys.remove();
                //如果是连接事件，注册读事件,并关联一个buffer
                if(key.isAcceptable()){
                    //有新的客户端连接，注册一个生成一个channel，
                    SocketChannel socketChannelRead = socketChannel.accept();
                    log.debug("新注册读的channel: {}", socketChannelRead.hashCode());
                    socketChannelRead.configureBlocking(false);
                    //注册一个读事件(如果接下来需要写，可以注册写事件)
                    socketChannelRead.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                } else if(key.isReadable()) {
                    try {
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        int i = channel.read(buffer);
                        if(i ==-1) {
                            //客户端正常断开
                            key.cancel();
                        } else {
                            System.out.println("客户端传来： "+ new String(buffer.array()));
                        }
                    } catch (IOException e) {
                        //将当前事件消除，否则select()方法会认为这个事件还没有处理
                        key.cancel();
                    }
                }
            }
        }
    }
}
