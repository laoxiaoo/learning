package com.xiao.jvm.classLoader;


import org.junit.Test;

/**
 * @author Malone Xiao
 * @ClassName TestStaticNotInit.java
 * @createTime 2021年06月03日 21:38:00
 */
public class TestStaticNotInit {

    public  int i;

    public static int j;

    public static final int k = 1;

    @Test
    public void test() throws Exception {
        System.out.println(Order.num);

        ClassLoader.getSystemClassLoader().loadClass("com.xiao.classLoader.Order");
    }

}

class Order {
    static {
        System.out.println("输出...");
    }
    public static final int num = 1;
}
