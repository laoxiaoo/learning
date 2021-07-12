
package com.xiao;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;
import java.util.Enumeration;


/**
 * @author malone xiao
 * @ClassName Test1.java
 * @Description
 * @createTime 2021年05月25日 10:51:00
 */

public class Test1 {

    private static String URL = "tcp://192.168.10.134:61616";

    @Test
    public void provider() throws Exception {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(URL);
        Connection connection = factory.createConnection();
        connection.start();
        //创建session， 事务，是否签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建目的地
        Queue queue = session.createQueue("queue1");
        //创建消息的生产者
        MessageProducer producer = session.createProducer(queue);
        //生产三条消息
        for(int i=0; i<3; i++){
            //消息发送mq
            TextMessage textMessage = session.createTextMessage("msg+" + i);
            textMessage.setStringProperty("testP1", "111234");
            producer.send(textMessage);
        }
        //session.commit();
        //关闭资源
        producer.close();
        session.close();
        connection.close();
    }

    @Test
    public void consumer() throws Exception {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(URL);
        Connection connection = factory.createConnection();
        connection.start();
        Enumeration jmsxPropertyNames = connection.getMetaData().getJMSXPropertyNames();
        while (jmsxPropertyNames.hasMoreElements()) {
            System.out.println(jmsxPropertyNames.nextElement());;
        }
        //创建session， 事务，是否签收
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
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

    @Test
    public void providerTopic() throws Exception {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(URL);
        Connection connection = factory.createConnection();
        connection.start();
        //创建session， 事务，是否签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建目的地
        Topic topic = session.createTopic("topic-1");
        //创建消息的生产者
        MessageProducer producer = session.createProducer(topic);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        //生产三条消息
        for(int i=0; i<3; i++){
            //消息发送mq
            producer.send(session.createTextMessage("msg+"+i));
        }
        //关闭资源
        producer.close();
        session.close();
        connection.close();
    }


    @Test
    public void consumerTopic1() throws Exception {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(URL);
        Connection connection = factory.createConnection();
        connection.start();
        //创建session， 事务，是否签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建目的地
        Topic topic = session.createTopic("topic-1");
        //创建生产者
        MessageConsumer consumer = session.createConsumer(topic);

        consumer.setMessageListener((message)->{
            System.out.println("=======> topic1");
            if(message!=null && message instanceof TextMessage){
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println(textMessage.getText());
                }catch (Exception e){

                }
            }
        });
        //保持控制台状态
        System.in.read();
        consumer.close();
        session.close();
        connection.close();
    }
    @Test
    public void consumerTopic2() throws Exception {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(URL);
        Connection connection = factory.createConnection();
        connection.start();
        //创建session， 事务，是否签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建目的地
        Topic topic = session.createTopic("topic-1");
        //创建生产者
        MessageConsumer consumer = session.createConsumer(topic);

        consumer.setMessageListener((message)->{
            System.out.println("=======> topic2");
            if(message!=null && message instanceof TextMessage){
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println(textMessage.getText());
                }catch (Exception e){

                }
            }
        });
        //保持控制台状态
        System.in.read();
        consumer.close();
        session.close();
        connection.close();
    }



}

