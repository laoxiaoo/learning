package com.xiao.consumer;

import com.xiao.base.ProductBase;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * @author lao xiao
 * @create 2022年05月28日 14:30:00
 */
@Slf4j
public class SortConsumer {

    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("CG");
        consumer.setNamesrvAddr(ProductBase.ADDRESS);
        consumer.subscribe("tx-topic", "*");
        //设置从第一个消息开始消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        //设置消费模式：默认集群
        consumer.setMessageModel(MessageModel.CLUSTERING);
        //注册监听
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {

                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        consumer.start();
    }

}
