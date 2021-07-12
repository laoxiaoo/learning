package com.xiao.m;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQPrefetchPolicy;

import javax.jms.*;

/**
 *
 * 测试生产者
 *
 * @author xiao ji hao
 * @create 2021年07月01日 11:13:00
 */
public class TestProvider {
    public static String URL = "tcp://192.168.1.131:61616";

    public static void main(String[] args) throws Exception {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(URL);
        ActiveMQPrefetchPolicy p = new ActiveMQPrefetchPolicy();
        // 设置prefetch 值(多个消费者有用)
        p.setQueuePrefetch(100);
        factory.setPrefetchPolicy(p);

        Connection connection = factory.createConnection();
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("queue1");
        MessageProducer producer = session.createProducer(queue);
        //生产三条消息
        for(int i=0; i<10; i++){
            //消息发送mq
            TextMessage textMessage = session.createTextMessage("msg+" + i);
            textMessage.setStringProperty("testP1", "111234");
            producer.send(textMessage);
        }
        session.commit();
        //关闭资源
        producer.close();
        session.close();
        connection.close();
    }

}
