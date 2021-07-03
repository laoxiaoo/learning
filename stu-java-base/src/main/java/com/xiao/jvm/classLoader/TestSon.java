package com.xiao.jvm.classLoader;

/**
 * @author Malone Xiao
 * @ClassName TestSon.java
 * @Description
 * @createTime 2021年05月25日 23:39:00
 */
public class TestSon {
    public static void main(String[] args) {
        Father father = new Son();
        father.print();
    }
}

class Father {
    int i= 10;
    Father() {
        this.print();
        i = 20;
    }
    public void print() {
        System.out.println(i);
    }
}

class Son extends Father {
    int i = 30;
    Son() {
        this.print();
        i = 40;
    }
    public void print() {
        System.out.println(i);
    }
}
