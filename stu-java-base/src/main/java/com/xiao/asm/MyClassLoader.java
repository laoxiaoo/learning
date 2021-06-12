package com.xiao.asm;

import java.net.URL;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName MyClassLoader.java
 * @Description
 * @createTime 2021年06月11日 18:35:00
 */
public class MyClassLoader extends ClassLoader {
    public Class defineClass(String name, byte[] b) {
        return defineClass(name, b, 0, b.length);
    }
}
