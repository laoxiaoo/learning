package com.xiao.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * 消费者接受消息的简单消费者
 *
 * @author xiao ji hao
 * @create 2021年08月03日 08:25:00
 */
@Slf4j
@Service
@RocketMQMessageListener(topic = "test-topic-1", consumerGroup = "my-consumer_test-topic-1")
public class MyConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        log.debug("收到消息：{}", message);
    }
}
