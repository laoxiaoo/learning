package com.xiao.jvm.gc;

/**
 * @author Malone Xiao
 * @ClassName TestAccessibility.java
 * @Description
 *  可达性分析
 *
 *  -Xms6m -Xmx6m -XX:+PrintGCDetails
 *
 * @createTime 2021年05月19日 23:19:00
 */
public class TestAccessibility {

    public  void gc1() {
        {
            byte[] buffer = new byte[1024*1024];
        }
        System.gc();
    }

    public  void gc2() {
        {
            byte[] buffer = new byte[1024*1024];
        }
        int i = 10;
        System.gc();
    }

    public static void main(String[] args) {
        new TestAccessibility().gc1();
    }
}
