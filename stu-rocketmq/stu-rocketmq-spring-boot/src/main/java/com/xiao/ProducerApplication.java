package com.xiao;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xiao ji hao
 * @create 2021年08月02日 22:46:00
 */
@SpringBootApplication
@Slf4j
public class ProducerApplication implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        //同步发送
        //rocketMQTemplate.convertAndSend("test-topic-1", "Hello, World!");
        //异步发送
        rocketMQTemplate.asyncSend("test-topic-1", "asyn spring message ", new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.debug("异步发送...{}", sendResult);
            }

            @Override
            public void onException(Throwable e) {
                log.error("异步发送异常...{}", e);
            }
        });
    }

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }
}
