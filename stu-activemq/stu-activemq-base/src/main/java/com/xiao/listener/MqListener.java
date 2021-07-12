package com.xiao.listener;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author malone xiao
 * @ClassName MqListener.java
 * @Description
 * @createTime 2021年05月25日 19:23:00
 */
@Component
public class MqListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        if(message!=null && message instanceof TextMessage){
            TextMessage textMessage = (TextMessage)message;
            try {
                System.out.println(textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
