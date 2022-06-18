package com.xiao.io;

import java.io.InputStream;

/**
 * @author lao xiao
 * @create 2022年05月22日 21:54:00
 */
public class Resource {

    /**
     * 根据项目的路径，获取配置文件的输入流
      * @param path
     * @return
     */
    public static InputStream getResourceAsStream(String path) {
        return Resource.class.getClassLoader().getResourceAsStream(path);
    }

}
