package com.xiao.netty.buffer;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * @author Malone Xiao
 * @create 2021年06月15日 10:10:00
 */
public class TestChannel {

    @Test
    public void transform() {
        try(FileChannel from = new FileInputStream("d:\\1.txt").getChannel();
            FileChannel to = new FileOutputStream("d:\\2.txt").getChannel();
        ) {
            //操作效率高，底层会调用0拷贝进行优化
            from.transferTo(0,from.size(), to);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
