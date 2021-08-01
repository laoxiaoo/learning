package com.xiao.product;

import com.xiao.base.ProductBase;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.junit.Test;

/**
 * @author xiao ji hao
 * @create 2021年07月31日 18:07:00
 */
@Slf4j
public class ProductTest extends ProductBase {

    @Test
    public void product() throws Exception {
        //定义一个消息
        Message msg = new Message(TOPIC /* Topic */,
                "TagA" /* Tag */,
                ("Hello RocketMQ ").getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
        );
        //会返回同步返回ack状态
        SendResult sendResult = producer.send(msg);
        System.out.printf("%s%n", sendResult);
        producer.shutdown();
    }

    @Test
    public void productSync() throws Exception {
        Message msg = new Message(TOPIC,
                "TagA",
                "OrderID188",
                "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));
        producer.send(msg, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {

                log.debug("消息： {}",  sendResult.getMsgId());
            }
            @Override
            public void onException(Throwable e) {
                e.printStackTrace();
            }
        });
        Thread.sleep(10000l);
        producer.shutdown();
    }

}
