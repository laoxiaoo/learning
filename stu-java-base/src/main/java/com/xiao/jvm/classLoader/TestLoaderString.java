package com.xiao.jvm.classLoader;

/**
 * @author Malone Xiao
 * @ClassName TestLoaderString.java
 * @createTime 2021年06月02日 23:25:00
 */
public class TestLoaderString {

    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("java.lang.String");
    }

}
