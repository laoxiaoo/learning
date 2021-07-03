package com.xiao.jvm.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Malone Xiao
 * @ClassName TestHeap01.java
 * @Description
 *  -Xms600m -Xmx600m
 *
 * @createTime  2021年05月09日 16:34:00
 */
public class TestHeap01 {

    public static void main(String[] args) {
        try {
            List<byte[]> list = new ArrayList<>();
            for (long i=0; i<100000000000000l; i++) {
                list.add(new byte[1024]);
                if(list.size() > 100){
                    list.clear();
                }
            }
            //System.gc();
            //一定会强制调用失去引用的finalize（）方法

            //System.runFinalization();
        } catch (Exception e) {

        }
    }

}
