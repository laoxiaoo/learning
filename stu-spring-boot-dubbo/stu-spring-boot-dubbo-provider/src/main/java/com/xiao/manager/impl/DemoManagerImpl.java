package com.xiao.manager.impl;

import com.xiao.manager.DemoManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import java.time.LocalDateTime;

/**
 * @author malone xiao
 * @create 2021年06月07日 17:57:00
 */
@DubboService(version = "1.0.0", cluster = "failfast", retries = 1)
@Slf4j
public class DemoManagerImpl implements DemoManager {
    @Override
    public String sayHello(String name) {
        log.info("来自{} 的请求...{}", LocalDateTime.now(), name);
        /*try {
            //Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        throw new RuntimeException("测试异常");
        //return "收到:"+name;
    }
}
