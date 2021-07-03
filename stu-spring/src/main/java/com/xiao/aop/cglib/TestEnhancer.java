package com.xiao.aop.cglib;

import org.springframework.cglib.core.DefaultGeneratorStrategy;
import org.springframework.cglib.proxy.Enhancer;

import java.nio.charset.StandardCharsets;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName TestEnhancer.java
 * @Description
 * @createTime 2021年06月11日 21:58:00
 */
public class TestEnhancer {

    public static void main(String[] args) {
        Enhancer e = new Enhancer();
        // etc.
        e.setSuperclass(String.class);
        e.setStrategy(new DefaultGeneratorStrategy() {
            protected byte[] transform(byte[] b) {
                // do something with bytes here
                return new String("test").getBytes(StandardCharsets.UTF_8);
            }
        });
        Object obj = e.create();
    }
}
