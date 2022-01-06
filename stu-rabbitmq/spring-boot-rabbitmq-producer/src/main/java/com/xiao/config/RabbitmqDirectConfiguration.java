package com.xiao.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiao ji hao
 * @create 2021年12月27日 20:43:00
 */
@Configuration
public class RabbitmqDirectConfiguration {

    @Bean(name = "direct.queue_03")
    public Queue commonQueue03() {
        QueueBuilder durable = QueueBuilder.durable("direct.queue_03");
        //消息过期后，就进入createOrder 的key绑定的队列中
        return durable.build();
    }

    @Bean
    public Binding directQueueBindToDirectQueue03(@Qualifier("direct.exchange") Exchange exchange, @Qualifier("direct.queue_03") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("queue_03").noargs();
    }

}
