package com.xiao.faction;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author malone xiao
 * @ClassName CommonChannelFactory.java
 * @Description
 * @createTime 2021年06月04日 10:28:00
 */
public class CommonChannelFactory implements ChannelFactory {

    public static final String  EXCHANGE_NAME = "exchange_demo";
    public static final String ROUTING_KEY = "routing_key_demo";
    public static final String QUEUE_NAME = "queue_demo";

    @Override
    public void execution(Consumer<Channel> consumer) throws Exception {
        //创建信道
        Connection connection = getConnection();
        Channel channel= connection.createChannel();
        //创建一个 type ＝ "direct", 持久化的、非自动删除的交换器
        channel.exchangeDeclare(EXCHANGE_NAME,  "direct", true , false , null );
        //创建一个持久化、非排他的、非自动删除的队列
        channel.queueDeclare(QUEUE_NAME , true , false , false , null);
        //将交换器与队列通过路由键绑定
        channel.queueBind (QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY) ;
        consumer.accept(channel);
        channel.close();
        connection.close();
    }

    @Override
    public void consumer(Function<Channel, com.rabbitmq.client.Consumer> function) throws Exception {
        Connection connection = getConnection();
        Channel channel= connection.createChannel();

        com.rabbitmq.client.Consumer consumer = function.apply(channel);
        channel.basicConsume(QUEUE_NAME,false, consumer);
        //保持控制台状态
        System.in.read();
        channel.close();
        connection.close();
    }
}
