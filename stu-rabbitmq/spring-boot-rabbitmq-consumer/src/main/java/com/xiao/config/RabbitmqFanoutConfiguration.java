package com.xiao.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * fanout模式
 *
 * </b>
 * fanout会将消息转发所有与当前交换机绑定的队列
 *
 * @author xiao ji hao
 * @create 2021年12月26日 14:42:00
 */
@Configuration
public class RabbitmqFanoutConfiguration {

    /**
     * 声明绑定交换机
     * @return
     */
    @Bean(name = "fanout.exchange")
    public Exchange fanoutExchange() {
        return ExchangeBuilder
                .fanoutExchange("fanout.exchange") //绑定fanout模式
                .durable(true) //消息进行持久化
                .build() ;
    }

    /**
     * 声明队列
     * @return
     */
    @Bean(name = "fanout.queue_01")
    public Queue commonQueue01() {
        return QueueBuilder.durable("fanout.queue_01").build() ;
    }

    @Bean(name = "fanout.queue_02")
    public Queue commonQueue02() {
        return QueueBuilder.durable("fanout.queue_02").build() ;
    }

    /**
     * 完成队列和交换机的绑定
     * @param exchange
     * @param queue
     * @return
     */
    @Bean
    public Binding fanoutQueueBindToFanoutQueue01(@Qualifier(value = "fanout.exchange") Exchange exchange ,
                                                @Qualifier(value = "fanout.queue_01") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("").noargs() ;
    }
    @Bean
    public Binding fanoutQueueBindToFanoutQueue02(@Qualifier(value = "fanout.exchange") Exchange exchange ,
                                                @Qualifier(value = "fanout.queue_02") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("fanout_queue_02").noargs() ;
    }


}
