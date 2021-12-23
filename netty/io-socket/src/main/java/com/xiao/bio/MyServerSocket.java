package com.xiao.bio;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author xiao ji hao
 * @create 2021年10月27日 23:18:00
 */
public class MyServerSocket {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = null;
        InputStream inputStream = null;
        try {
            serverSocket = new ServerSocket(80);
            //阻塞的获取一个套接字
            Socket accept = serverSocket.accept();
            //读取网络流
            inputStream = accept.getInputStream();
            byte[] buf = new byte[1024];
            int read = inputStream.read(buf);
            System.out.println(new String(buf, 0, read, "utf-8"));
            accept.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
            serverSocket.close();
        }
    }

}
