package com.xiao.dualDataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;

/**
 * @author xiao ji hao
 * @create 2021年08月11日 20:07:00
 */
@SpringBootApplication(exclude = {DataSourceTransactionManagerAutoConfiguration.class})
public class DualDataSourceMain {

    public static void main(String[] args) {
        SpringApplication.run(DualDataSourceMain.class, args);
    }

}
