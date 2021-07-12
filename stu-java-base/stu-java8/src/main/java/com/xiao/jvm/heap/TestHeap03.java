package com.xiao.jvm.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Malone Xiao
 * @ClassName TestHeap03.java
 * @Description
 * @createTime 2021年05月09日 23:19:00
 */
public class TestHeap03 {

    public static void main(String[] args) {
        Long time = System.currentTimeMillis();
        for (int i = 0; i<10000000; i++) {
            method();
        }
        System.out.println(System.currentTimeMillis() - time);
    }

    public static void method() {
        List list = new ArrayList<>();
    }

}
