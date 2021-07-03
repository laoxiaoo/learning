package com.xiao.jvm.stack;

/**
 * @author Malone Xiao
 * @ClassName TestVaribale.java
 * @Description
 * @createTime 2021年05月04日 23:55:00
 */
public class TestLocalVariable {

    public static void main(String[] args) {
        new TestLocalVariable().method1();
    }

    public int method1() {
        int i = 10;
        int j = 20;

        return (int) (new TestLocalVariable().method2()+i+j);
    }

    public double method2() {
        return 1.0d + 2.0d;
    }

}
