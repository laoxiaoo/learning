package com.xiao.test;

/**
 * @author lao xiao
 * @date 2023-04-04 17:26
 */
public class Father {
    int i= 10;
    Father() {
        this.print();
        i = 20;
    }
    public void print() {
        System.out.println(i);
    }
}
