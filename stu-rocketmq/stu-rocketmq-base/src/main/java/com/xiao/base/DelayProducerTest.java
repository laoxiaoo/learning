package com.xiao.base;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.time.LocalDateTime;

/**
 *
 * 延迟消息队列
 *
 *
 * @author xiao ji hao
 * @create 2021年08月01日 10:51:00
 */
@Slf4j
public class DelayProducerTest extends ProductBase {

    public static void main(String[] args) throws Exception {
        Message msg = new Message("TOPIC",
                "TagA",
                "DelayOrderID188",
                "Delay Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));
        //设置消息延迟等级为3（也就是10s）
        msg.setDelayTimeLevel(3);
        SendResult send = producer.send(msg);
        log.debug("延迟消息队列：{}", LocalDateTime.now());
        producer.shutdown();
    }

}
