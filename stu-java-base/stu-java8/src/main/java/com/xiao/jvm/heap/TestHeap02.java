package com.xiao.jvm.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Malone Xiao
 * @ClassName TestHeap02.java
 * @Description
 *
 *  -Xms9m -Xmx9m -XX:+PrintGCDetails
 * @createTime 2021年05月09日 20:33:00
 */
public class TestHeap02 {

    public static void main(String[] args) {
        try{
            String name = "laoxiaolaoxiao";
            List<String> list = new ArrayList<>();
            while (Boolean.TRUE) {
                name = name+name;
                list.add(name);
            }

        } catch (Exception e) {

        }
    }

}
