package com.xiao.diamond;

/**
 * @author xiao jie
 * @create 2022年01月18日 16:26:00
 */
public interface B extends A{

    default void show() {
        System.out.println("B");
    }
}
