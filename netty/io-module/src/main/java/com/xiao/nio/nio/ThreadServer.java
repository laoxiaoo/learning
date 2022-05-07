package com.xiao.nio.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 *
 * 多线程版的server
 *
 * @author xiao ji hao
 * @create 2022年05月01日 16:37:00
 */
@Slf4j
public class ThreadServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(80));
        ssc.configureBlocking(Boolean.FALSE);
        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        Worker[] workers = new Worker[2];
        for(int i=0; i < workers.length; i++) {
            workers[i] = new Worker("worker-"+i);
        }
        int i =0;
        while (true) {
            selector.select();
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                keyIterator.remove();
                if(key.isAcceptable()) {
                    SocketChannel socketChannel = ssc.accept();
                    socketChannel.configureBlocking(Boolean.FALSE);
                    workers[i++ % workers.length].register(socketChannel);
                }
            }
        }
    }

    static class Worker implements Runnable {

        private String name;

        //一个线程对应一个selector，一个selector关联多个key
        private Selector selector;
        private Thread thread;
        //是否开始
        private volatile boolean isStart = false;

        private ConcurrentLinkedQueue<Supplier> queue = new ConcurrentLinkedQueue();

        public Worker(String name) {
            this.name = name;
        }

        public void register(SocketChannel sc) throws IOException {
            if(!isStart) {
                selector = Selector.open();
                thread = new Thread(this, name);
                thread.start();
                isStart = true;
            }
            queue.add(() -> {
                SelectionKey key = null;
                try {
                    key = sc.register(selector, SelectionKey.OP_READ);
                } catch (ClosedChannelException e) {
                    e.printStackTrace();
                }
                return key;
            });
            selector.wakeup();
        }


        @Override
        public void run() {
            while (true) {
                try{
                    selector.select();
                    Supplier supplier = queue.poll();
                    if(Objects.nonNull(supplier)) {
                        supplier.get();
                    }
                    Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                    if(keyIterator.hasNext()) {
                        SelectionKey key = keyIterator.next();
                        keyIterator.remove();
                        try{
                            if(key.isReadable()) {
                                SocketChannel socketChannel = (SocketChannel) key.channel();
                                ByteBuffer buffer = ByteBuffer.allocate(1024);
                                int read = socketChannel.read(buffer);
                                if(read == -1) {
                                    key.cancel();
                                }
                                log.info("read data: {}", new String(buffer.array()));
                            }
                        }catch (Exception e) {
                            e.printStackTrace();
                            key.cancel();
                        }
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
