package com.xiao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * @author xiao ji hao
 * @create 2022年01月02日 09:38:00
 */
@SpringBootApplication
public class ProducerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ProducerApplication.class, args);
        testEnvironment(context.getBean(KafkaTemplate.class));
    }


    public static void testEnvironment(KafkaTemplate kafkaTemplate) {
        kafkaTemplate.send("my_test", "测试java发送数据");
    }

}
