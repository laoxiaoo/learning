package com.xiao;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author malone xiao
 * @ClassName DubboConsumer.java
 * @Description
 * @createTime 2021年06月07日 17:47:00
 */
@SpringBootApplication
public class DubboConsumer {

    public static void main(String[] args) {
        SpringApplication.run(DubboConsumer.class, args);
    }
}
