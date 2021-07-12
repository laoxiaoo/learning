package com.xiao.config;


import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.converter.SimpleMessageConverter;

import javax.jms.MessageListener;
import java.util.Properties;

/**
 * @author malone xiao
 * @ClassName JmsConfig.java
 * @Description
 * @createTime 2021年05月25日 16:07:00
 */
@Configuration
public class JmsConfig {

    public final static String URL = "tcp://192.168.1.131:61616";

    @Bean
    public PooledConnectionFactory jmsFactory(){
        PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
        Properties properties = new Properties();
        properties.setProperty("brokerURL", URL);
        pooledConnectionFactory.setProperties(properties);
        //最大连接数
        pooledConnectionFactory.setMaxConnections(100);
        return pooledConnectionFactory;
    }


    /**
     * 队列的目的地，点对点的
     * @return
     */
    @Bean
    public ActiveMQQueue destinationQueue() {
        //设置目的地的队列名称
        ActiveMQQueue activeMQQueue = new ActiveMQQueue("spring-activemq-queue");
        return activeMQQueue;
    }

    @Bean
    public JmsTemplate jmsTemplateQueue(PooledConnectionFactory jmsFactory, ActiveMQQueue destinationQueue) {
        JmsTemplate jmsTemplate = new JmsTemplate();
        //设置jms的配置
        jmsTemplate.setConnectionFactory(jmsFactory);
        //设置目的地
        jmsTemplate.setDefaultDestination(destinationQueue);
        jmsTemplate.setMessageConverter(new SimpleMessageConverter());
        return jmsTemplate;
    }

    @Bean
    public DefaultMessageListenerContainer jmsListener(PooledConnectionFactory jmsFactory,
                                                       ActiveMQQueue destinationQueue,
                                                       MessageListener messageListner) {
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(jmsFactory);
        container.setDestination(destinationQueue);
        container.setMessageListener(messageListner);
        return container;
    }

}
