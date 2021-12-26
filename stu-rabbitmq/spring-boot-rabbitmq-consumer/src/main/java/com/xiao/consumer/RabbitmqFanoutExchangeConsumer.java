package com.xiao.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

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

}
