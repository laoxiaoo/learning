package com.xiao.nio.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 *
 * 示例
 * @author xiao ji hao
 * @create 2022年05月01日 14:26:00
 */
@Slf4j
public class WriterServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(80));
        ssc.configureBlocking(Boolean.FALSE);
        Selector selector = Selector.open();
        SelectionKey selectionKey = ssc.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            selector.select();
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                keyIterator.remove();
                if(key.isAcceptable()) {
                    SocketChannel socketChannel = ssc.accept();
                    socketChannel.configureBlocking(Boolean.FALSE);
                    SelectionKey sk = socketChannel.register(selector, SelectionKey.OP_READ);
                    //如果是连接事件,造出大量的数据，模拟发送客户端
                    StringBuffer sb = new StringBuffer();
                    for (int i=0; i<30000000; i++) {
                        sb.append("a");
                    }
                    ByteBuffer buffer = Charset.defaultCharset().encode(sb.toString());
                    /*//只要缓存区还有数据，就一直发送
                    while(buffer.hasRemaining()) {
                        int write = socketChannel.write(buffer);
                        log.info("发送数据：{}", write);
                    }*/
                    int i = socketChannel.write(buffer);
                    log.info("第一次发送数据：{}", i);
                    if(buffer.hasRemaining()) {
                        //如果还有没有写完的数据，则关注一个可写事件，用于下次处理
                        //这里的意思：同时关注原来关注的事件和可写事件
                        sk.interestOps(sk.interestOps() + SelectionKey.OP_WRITE);
                        //并且将当前没发完的保存起来
                        sk.attach(buffer);
                    }
                }else if(key.isWritable()) {
                    //如果是可写事件，继续发送数据

                    SocketChannel channel = (SocketChannel)key.channel();
                    //取出上一次可写事件的key关联的数据
                    ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
                    int i = channel.write(byteBuffer);
                    log.info("再次写入数据： {}", i);
                    if(!byteBuffer.hasRemaining()) {
                        //没有数据了则清空，避免内存泄露
                        key.attach(null);
                        //同时不再关注可写事件
                        key.interestOps(key.interestOps() - SelectionKey.OP_WRITE);
                    }
                }
            }
        }

    }

}
