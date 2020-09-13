package com.xiao.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @program: STU_Netty
 * @description: 利用channel进行copy操作
 * @author: xiao
 * @create: 2020-08-04 23:47
 */
public class FileChannelCopy {

    public static void main(String[] args) throws Exception {
        FileInputStream input = new FileInputStream("E:\\BaiduNetdiskDownload\\00 尚硅谷-云计算-Linux系统管理-行业介绍\\001 尚硅谷-云计算-Linux系统管理-行业介绍-IT主流行业1.avi");
        FileChannel channelSource = input.getChannel();
        FileOutputStream outputStream = new FileOutputStream("d:\\1.mp4");
        FileChannel channelTarget = outputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(5);
        while (true) {
            byteBuffer.clear();
            int read = channelSource.read(byteBuffer);
            if(read == -1){
                //读取完成
                break;
            }
            byteBuffer.flip();
            channelTarget.write(byteBuffer);
        }
    }
}