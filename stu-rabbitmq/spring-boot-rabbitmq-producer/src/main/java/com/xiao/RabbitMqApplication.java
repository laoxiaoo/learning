package com.xiao;

import com.xiao.config.RabbitmqTTLConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author xiao ji hao
 * @create 2021年12月26日 15:08:00
 */
@SpringBootApplication
@Slf4j
public class RabbitMqApplication {

    private static RabbitTemplate rabbitTemplate;

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(RabbitMqApplication.class, args);
        rabbitTemplate = applicationContext.getBean(RabbitTemplate.class);

        dlxExchangeMessageTransport();
    }

    /**
     * 测试FanoutExchange交换机的消息发送
     */
    private static void fanoutExchangeMessageTransport() {
        rabbitTemplate.convertAndSend("fanout.exchange" ,
                "fanout_queue_00" , "fanout exchange 测试数据");
    }

    private static void dlxExchangeMessageTransport() {
        log.info("发送数据完毕");
        //数据流动过程
        //direct.queue_02 ---> dlx.queue
        rabbitTemplate.convertAndSend(RabbitmqTTLConfiguration.DIRECT_EXCHANGE,
                RabbitmqTTLConfiguration.DIRECT_KEY, "测试过期数据...");

    }

}
