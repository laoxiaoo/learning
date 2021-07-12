package com.xiao.m;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Enumeration;

import static com.xiao.m.TestProvider.URL;

/**
 * @author xiao ji hao
 * @create 2021年07月01日 11:40:00
 */
public class TestConsumer {

    public static void main(String[] args) throws Exception {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(URL);
        Connection connection = factory.createConnection();
        connection.start();
        Enumeration jmsxPropertyNames = connection.getMetaData().getJMSXPropertyNames();
        while (jmsxPropertyNames.hasMoreElements()) {
            System.out.println(jmsxPropertyNames.nextElement());;
        }
        //创建session， 事务，是否签收
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        session.recover();
        //创建目的地
        Queue queue = session.createQueue("queue1");
        //创建生产者
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener((message)->{
            if(message!=null && message instanceof TextMessage){
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println(textMessage.getText());
                    textMessage.acknowledge();
                }catch (Exception e){

                }
            }

        });
        //session.commit();
        //保持控制台状态
        System.in.read();
        consumer.close();
        session.close();
        connection.close();
    }

}
