package com.xiao.consumer;

import com.xiao.base.ProductBase;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.junit.Test;

import java.util.List;

/**
 * @author xiao ji hao
 * @create 2021年07月31日 18:04:00
 */
@Slf4j
public class ConsumerTest  {

    @Test
    public void consumer() throws Exception {
        //定义一个push模式的消费者
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("CG");
        consumer.setNamesrvAddr(ProductBase.ADDRESS);
        consumer.subscribe("TopicTest", "*");
        //设置从第一个消息开始消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        //设置消费模式：默认集群
        consumer.setMessageModel(MessageModel.CLUSTERING);
        //注册监听
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
                //如果broker有消息，就会触发这个方法
                log.debug("{} Receive New Messages: {}", Thread.currentThread().getName(), msgs);
                //返回mq需要的消费状态
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
    }

}
