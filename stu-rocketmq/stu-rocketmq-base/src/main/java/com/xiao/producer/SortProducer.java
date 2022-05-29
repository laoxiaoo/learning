package com.xiao.producer;

import com.xiao.base.ProductBase;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 顺序消息的投递
 *
 * @author lao xiao
 * @create 2022年05月28日 14:13:00
 */
@Slf4j
public class SortProducer extends ProductBase {

    public static void main(String[] args) throws Exception {
        Message msg = new Message("TOPIC",
                "TagA",
                "SortOrderID188",
                "Sort Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));
        Integer orderId = 1;
        //模拟指定的orderId发送指定的队列之中
        SendResult send = producer.send(msg, new MessageQueueSelector() {
            @Override
            public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                Integer id = (Integer) arg;
                int index = id % mqs.size();
                return mqs.get(index);
            }
        }, orderId);
        producer.shutdown();
    }

}
