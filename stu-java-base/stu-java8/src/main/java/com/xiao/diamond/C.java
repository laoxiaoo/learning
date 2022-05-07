package com.xiao.diamond;

/**
 * @author xiao jie
 * @create 2022年01月18日 16:27:00
 */
public interface C extends A{

    default void show() {
        System.out.println("C");
    }
}
