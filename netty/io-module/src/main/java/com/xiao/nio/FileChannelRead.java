package com.xiao.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 利用channel读取文件
 */
public class FileChannelRead {
    public static void main(String[] args) throws Exception {
        File file = new File("d:\\1.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel channel = fileInputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(10);
        while (true) {
            int read = channel.read(buffer);
            if(read == -1) {
                break;
            }
            //切换读模式
            buffer.flip();
            //查询是否还有数据没读
            while (buffer.hasRemaining()) {
                System.out.println((char) buffer.get());
            }
            //清空缓存区
            buffer.clear();
        }
    }

}
