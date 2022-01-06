package com.xiao.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 当消息过期后，进入私信队列中
 * 1. 声明一个死信队列交换机
 * 2.声明一个私信队列
 * 3.完成死信队列与交换机的绑定
 * 4.将数据推送到一个设置了过期策略的队列
 * </br>
 * 当一个数据过期后，格局key寻找到对应的死信队列
 *
 * @author xiao ji hao
 * @create 2021年12月26日 16:49:00
 */
@Configuration
public class RabbitmqTTLConfiguration {

    public static String DLX_EXCHANGE = "dlx.exchange";

    public static String DLX_QUEUE = "dlx.queue";

    public static String DLX_KEY = "createOrderDlx";

    public static String DIRECT_KEY = "createOrder";

    public static String DIRECT_EXCHANGE = "direct.exchange";



    /**
     * 声明一个死信队列交换机
     * @return
     */
    @Bean(name = "dlx.exchange")
    public Exchange dlxExchange() {
        return ExchangeBuilder
                .directExchange(DLX_EXCHANGE)
                .durable(true) //消息进行持久化
                .build() ;
    }

    /**
     * 声明一个私信队列
     * @return
     */
    @Bean(name = "dlx.queue")
    public Queue dlxQueue() {
        return QueueBuilder.durable(DLX_QUEUE).build();
    }

    /**
     * 创建一个普通的direct的交换机
     * @return
     */
    @Bean(name = "direct.exchange")
    public Exchange directExchange() {
        return ExchangeBuilder
                .directExchange(DIRECT_EXCHANGE)
                .durable(true) //消息进行持久化
                .build() ;
    }

    /**
     * 创建一个过期队列，用于存放需要过期的数据
     * @return
     */
    @Bean(name = "direct.queue_02")
    public Queue commonQueue02() {
        QueueBuilder durable = QueueBuilder.durable("direct.queue_02");
        durable.ttl(30000);
        //指定如果消息过期，进入的私信交换机
        durable.deadLetterExchange(DLX_EXCHANGE);
        //消息过期，绑定的key
        durable.deadLetterRoutingKey(DLX_KEY);
        //消息过期后，就进入createOrder 的key绑定的队列中
        return durable.build();
    }

    @Bean
    public Binding directQueueBindToDirectQueue02(@Qualifier("direct.exchange") Exchange exchange, @Qualifier("direct.queue_02") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with(DIRECT_KEY).noargs();
    }


    /**
     * 完成死信队列与交换机的绑定
     * @param exchange
     * @param queue
     * @return
     */
    @Bean
    public Binding dlxQueueBindToDlxQueue(@Qualifier("dlx.exchange") Exchange exchange, @Qualifier("dlx.queue") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with(DLX_KEY).noargs();
    }


}
