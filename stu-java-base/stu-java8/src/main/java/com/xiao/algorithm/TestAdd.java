package com.xiao.algorithm;

/**
 * @author xiao ji hao
 * @create 2021年06月25日 00:10:00
 */
public class TestAdd {

    public static void main(String[] args) {
        System.out.println(add(2,2));
    }

    static int add(int a, int b) {
        while (b!=0) {
            int temp = a^b;
            b = (a&b)<<1;
            a = temp;
        }
        return a;
    }

}
