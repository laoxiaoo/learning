package com.xiao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

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

    public static void synSendMessage(KafkaTemplate kafkaTemplate) {
        kafkaTemplate.send("my_test" , "测试java发送异步数据").addCallback(obj -> {
            System.out.println("发送成功结果" + ((SendResult)obj).getProducerRecord().value());
        } , t -> System.out.println("失败结果:" + t.getMessage()));
    }

}
