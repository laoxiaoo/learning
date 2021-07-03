package com.xiao.jvm.classLoader;

/**
 * @author Malone Xiao
 * @ClassName TestStatic.java
 * @createTime 2021年06月03日 21:31:00
 */
public class TestStatic {
    public static int i = 1;

    public static int j;

    static {
        j = 2;
        System.out.println(j);
    }

}
