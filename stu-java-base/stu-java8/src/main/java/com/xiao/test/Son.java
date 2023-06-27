package com.xiao.test;

/**
 * @author lao xiao
 * @date 2023-04-04 17:26
 */
public class Son extends Father {
    private int i = 30;
    Son() {
        this.print();
        i = 40;
    }
    public void print() {
        System.out.println(i);
    }

    public static void main(String[] args) {
        Son son = new Son();
    }
}
