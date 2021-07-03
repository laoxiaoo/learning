package com.xiao.jvm.classLoader;

/**
 * @author Malone Xiao
 * @ClassName Dp1.java
 * @Description
 * @createTime 2021年04月29日 23:04:00
 */
public class Dp1 {

    public static int number = 1;

    static {
        number = 2;
        num = 3;
        //System.out.println(num);
    }
    private static int num = 4;

    public static void main(String[] args) {
        System.out.println("查看类加载的文件");
    }

}
