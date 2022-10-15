package com.xiao.server.impl;


import com.xiao.server.Server;

/**
 * @author lao xiao
 * @version 1.
 * @ClassName FtpServerImpl.java
 * @Description TODO
 * @createTime 2021年03月23日 09:06:00
 */
public class HttpServerImpl implements Server {
    @Override
    public void start() {
        System.out.println("http start.....");
    }

    @Override
    public void stop() {
        System.out.println("http end....");
    }
}
