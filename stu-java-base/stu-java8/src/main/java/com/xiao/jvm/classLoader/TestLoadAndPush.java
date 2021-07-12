package com.xiao.jvm.classLoader;


import org.junit.Test;

/**
 * @author Malone Xiao
 * @ClassName TestLoadAndPush.java
 * @createTime 2021年05月30日 18:25:00
 */
public class TestLoadAndPush {
    public static void main(String[] args) {
        System.out.println(2<<14);
    }

    public void pushConstLdc() {
        int i = -1;
        int a = 5;
        int b = 6;
        int c = 127;
        int d = 128;
        int e = 32767;
        int f = 32768;
    }

    @Test
    public void method1() {
        int i = 10;
        double j = i / 0.0;
        //Infinity
        System.out.println(j);
        //NaN
        System.out.println(0.0/0.0);
    }

    public void method2() {
        int i=10;
        int a = i++;

        int j=20;
        int c = ++j;
    }

    public void method3() {
        int i=10;
        i=i++;
        //10
        System.out.println(i);
    }

    public void method4() {
        System.out.println("hello");
    }

    public int method5() {

        int a=10;
        a+=a;
        if(a == 0) {
            return 20;
        } else {
            return 5;
        }
    }

    public void method6(int select) {
        int num;
        switch (select) {
            case 1:
                num=10;
                break;
            case 2 : {
                num = 20;
                break;
            }
            default:
                num=30;
        }
    }

    public String method7() {
        String str = "hello";
        try {
            return str;
        } finally {
            str = "laoxiao";
        }
    }

    public synchronized void method8() {

    }
}
