package com.xiao.bio;

import java.io.OutputStream;
import java.net.Socket;

/**
 * @author xiao ji hao
 * @create 2021年10月27日 23:25:00
 */
public class MyClientSocket {

    public static void main(String[] args) throws Exception {
        Socket socket = null;
        OutputStream outputStream = null;
        try {
            //建立socket对象，连接服务器
            socket = new Socket("127.0.0.1", 80);
            outputStream = socket.getOutputStream();
            outputStream.write("老肖".getBytes("utf-8"));
        } catch (Exception e) {

        } finally {
            outputStream.close();
            socket.close();
        }
    }

}
