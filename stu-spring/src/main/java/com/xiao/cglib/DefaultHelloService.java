package com.xiao.cglib;

/**
 * @author Malone Xiao
 * @create 2021年06月11日 23:20:00
 */
public class DefaultHelloService implements HelloService{
    @Override
    public void sayHello(String name) {
        System.out.println(name+" say hello ");
    }
}
