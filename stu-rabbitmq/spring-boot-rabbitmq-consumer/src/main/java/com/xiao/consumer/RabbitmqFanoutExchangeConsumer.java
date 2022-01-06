package com.xiao.consumer;

import com.rabbitmq.client.Channel;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author xiao ji hao
 * @create 2021年12月26日 15:17:00
 */
@Component
@Slf4j
public class RabbitmqFanoutExchangeConsumer {

    @RabbitListener(queues = "fanout.queue_01")
    public void fanoutExchangeQueue01(String messageBody) {
        log.info("fanout exchange queue 01 message is : {}" , messageBody);
    }

    @RabbitListener(queues = "fanout.queue_02")
    public void fanoutExchangeQueue02(String messageBody) {
        log.info("fanout exchange queue 02 message is : {}" , messageBody);
    }

    @RabbitListener(queues = "dlx.queue")
    public void dlxExchangeQueue(String messageBody) {
        log.info("dlx exchange queue 02 message is : {}" , messageBody);
    }

    @RabbitListener(queues = "direct.queue_03")
    public void directExchangeQueue03(String messageBody, Channel channel, Message message) {
        try {
            log.info("dlx exchange queue 02 message is : {}" , messageBody);
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), true, Boolean.TRUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
