package com.xiao.spi;

import java.util.ServiceLoader;

/**
 * 测试加载spi 类
 *
 * @author xiao ji hao
 * @create 2021年07月25日 20:04:00
 */
public class LoadTest {

    public static void main(String[] args) {
        ServiceLoader<UploadService> loaders = ServiceLoader.load(UploadService.class);
        for(UploadService loader : loaders) {
            loader.upload();
        }
    }

}
