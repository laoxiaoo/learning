package com.xiao;

import com.rabbitmq.client.*;
import com.xiao.faction.CommonChannelFactory;
import org.junit.Test;

import java.io.IOException;

/**
 * @author malone xiao
 * @ClassName TestQos.java
 * @createTime 2021年06月04日 14:54:00
 */
public class TestQos {

    @Test
    public void consumer() throws Exception {
        CommonChannelFactory factory = new CommonChannelFactory();
        factory.consumer(this::consumer);
    }

    private Consumer consumer(Channel channel) {
        try {
            channel.basicQos(3, false);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("receive message:"+new String(body));
                channel.basicAck(envelope.getDeliveryTag(), false);
                try {
                    Thread.sleep(10000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        return consumer;
    }

}
