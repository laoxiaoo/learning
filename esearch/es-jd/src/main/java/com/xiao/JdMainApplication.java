package com.xiao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientAutoConfiguration;

/**
 * 需要将
 *  ElasticsearchDataAutoConfiguration.class, ElasticsearchRestClientAutoConfiguration.class
 *  屏蔽，不然可能spring boot自动加载，可能产生不可预知的错误
 *
 * @author: lao xiao
 * @create: 2020-10-08 15:06
 */
@SpringBootApplication(exclude = { ElasticsearchDataAutoConfiguration.class, ElasticsearchRestClientAutoConfiguration.class})
public class JdMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(JdMainApplication.class, args);
    }
}