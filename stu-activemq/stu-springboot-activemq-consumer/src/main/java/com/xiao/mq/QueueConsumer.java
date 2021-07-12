package com.xiao.mq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * @author xiao ji hao
 * @create 2021年07月06日 20:21:00
 */
@Component
public class QueueConsumer {
    @JmsListener(destination = "spring-boot-queue")
    public void received(TextMessage textMessage) throws JMSException {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("收到消息：" + textMessage.getText()+"   "+System.currentTimeMillis());
    }
}
