package com.xiao.cglib;

/**
 * @author lonely xiao
 * @create 2021年06月11日 23:19:00
 */
public interface HelloService {
    void sayHello(String name);

    default void test() {

    }
}
