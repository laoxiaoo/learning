package com.xiao.jvm.classLoader;

/**
 * @author Malone Xiao
 * @ClassName TestInteger.java
 * @Description
 * @createTime 2021年05月25日 23:11:00
 */
public class TestInteger {

    public static void main(String[] args) {
        Integer i1 = 5;
        Integer i2= 5;
        System.out.println(i1 == i2);


    }

    public static void test1() {
        Integer i3 = 128;
        int i4 = 128;
        System.out.println(i3 == i4);
    }

}
