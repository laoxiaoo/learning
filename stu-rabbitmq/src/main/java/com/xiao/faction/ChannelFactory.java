package com.xiao.faction;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 *
 *
 * @author malone xiao
 * @ClassName ChannelFactory.java
 * @Description
 * @createTime 2021年06月04日 10:19:00
 */
public interface ChannelFactory {

    String IP_ADDRESS = "192.168.10.134";
    int PORT= 5672;

    /**
     * 获取连接
     *
     * @return
     * @throws Exception
     */
    default Connection getConnection() throws Exception {
        ConnectionFactory factory= new ConnectionFactory() ;
         /*factory.setHost(IP_ADDRESS) ;
        factory.setPort (PORT) ;
        factory.setUsername ("root" ) ;
        factory.setPassword ("root") ;*/
        //创建连接
        factory.setUri("amqp://root:root@"+IP_ADDRESS+":"+PORT);
        return factory.newConnection();
    }

    /**
     * 推送消息
     *
     * @param consumer
     * @return
     * @throws Exception
     */
    void execution(Consumer<Channel> consumer) throws Exception;


    /**
     * 消费消息
     * @param function
     * @throws Exception
     */
    void consumer(Function<Channel, com.rabbitmq.client.Consumer> function) throws Exception;

}
