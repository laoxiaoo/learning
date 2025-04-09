package com.xiao.channel;


import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author lao xiao
 * @date 2025-04-09 10:17
 */
@Slf4j
public class ChannelTest {

    public static void main(String[] args) {
        try (FileInputStream fi = new FileInputStream("./1.text");
             FileChannel fileChannel = fi.getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate(10);
            while (true) {
                int read = fileChannel.read(buffer);
                if (read == -1) {
                    break;
                }
                buffer.flip();
                while (buffer.hasRemaining()) {
                    log.info("read:{}", (char) buffer.get());
                }
                buffer.clear();
            }
        } catch (Exception e) {
            log.error("invoke main error ", e);
        }
    }

}
