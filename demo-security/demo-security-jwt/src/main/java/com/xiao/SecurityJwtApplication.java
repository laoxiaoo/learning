package com.xiao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName SecurityJwtApplication.java
 * @Description
 * @createTime 2021年01月20日 23:05:00
 */
@SpringBootApplication
@MapperScan("com.xiao.dao")
public class SecurityJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityJwtApplication.class, args);
    }
}
