package xiao.server.impl;

import xiao.server.Server;

/**
 * @author 肖杰
 * @version 1.
 * @ClassName FtpServerImpl.java
 * @Description TODO
 * @createTime 2021年03月23日 09:06:00
 */
public class FtpServerImpl implements Server {
    @Override
    public void start() {
        System.out.println("ftp start.....");
    }

    @Override
    public void stop() {
        System.out.println("ftp end....");
    }
}
