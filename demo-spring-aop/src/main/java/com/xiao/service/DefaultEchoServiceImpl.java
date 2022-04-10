package com.xiao.service;

/**
 * @author xiao ji hao
 * @create 2022年02月16日 20:42:00
 */
public class DefaultEchoServiceImpl implements EchoService{
    @Override
    public void sayHello(String name) {
        System.out.println("say hello"+name);
    }
}
