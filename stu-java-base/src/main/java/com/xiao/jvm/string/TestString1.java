package com.xiao.jvm.string;


import org.junit.Test;

/**
 * @author Malone Xiao
 * @ClassName TestString1.java
 * @Description
 * @createTime 2021年05月15日 16:24:00
 */
public class TestString1 {

    /**
     * 字面量定义的字符串常量
     *  当堆区的字符串常量修改时，会复制一份出来进行修改
     */
    @Test
    public void test1() {
        String a = "abc";
        String b = "abc";
        //true
        System.out.println(a==b);
        a = "dfe";
        // false
        System.out.println(a==b);
    }

    /**
     * 使用replace时，也是复制一份再进行修改
     */
    @Test
    public void test2() {
        String a = "abc";
        String b = "abc";
        b = a.replace('a', 'd');
        //abc
        System.out.println(a);
        //dbc
        System.out.println(b);
    }

    @Test
    public void test3() {
        String a = "a"+"b";
        String b = "ab";
        //true
        System.out.println(a==b);
        String c = "a";
        String d = c+"b";
        //false
        System.out.println(b==d);
        String f = d.intern();
        //true
        System.out.println(b==f);
    }

    @Test
    public void test4() {
        /**
         * 使用final修饰的常量是编译期优化
         * 所以，在代码中，建议 能使用final修饰的使用final修饰
         */
        final String a = "a";
        final String b = "b";
        String c = "ab";
        String d = a+b;
        //true
        System.out.println(c == d);
    }
}
