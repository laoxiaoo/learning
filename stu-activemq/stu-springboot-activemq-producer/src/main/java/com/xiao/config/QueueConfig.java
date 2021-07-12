package com.xiao.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;

/**
 * @author xiao ji hao
 * @create 2021年07月06日 20:01:00
 */
@Configuration
public class QueueConfig {

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("spring-boot-queue");
    }

}
