package com.xiao.mysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;

/**
 * @author xiao ji hao
 * @create 2021年08月11日 20:07:00
 */
@SpringBootApplication
public class LineApplicationMain {

    public static void main(String[] args) {
        SpringApplication.run(LineApplicationMain.class, args);
    }

}
