package com.xiao;

import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;

/**
 *  rabbitmq hello world
 *
 * @author malone xiao
 * @ClassName TestHelloword.java
 * @createTime 2021年05月31日 14:50:00
 */
public class TestHelloWorld {
    private static final String  EXCHANGE_NAME = "exchange_demo";
    private static final String ROUTING_KEY = "routing_key_demo";
    private static final String QUEUE_NAME = "queue_demo";
    private static final String IP_ADDRESS = "192.168.10.134";
    private static final int PORT= 5672;

    @Test
    public void producer() throws Exception {
        ConnectionFactory factory= new ConnectionFactory() ;
        factory.setHost(IP_ADDRESS) ;
        factory.setPort (PORT) ;
        factory.setUsername ("root" ) ;
        factory.setPassword ("root") ;
        //创建连接
        Connection connection= factory.newConnection();
        //创建信道
        Channel channel= connection.createChannel();
        //创建一个 type ＝ "direct", 持久化的、非自动删除的交换器
        channel.exchangeDeclare(EXCHANGE_NAME,  "direct", true , false , null );
        //创建一个持久化、非排他的、非自动删除的队列
        channel.queueDeclare(QUEUE_NAME , true , false , false , null);
        //将交换器与队列通过路由键绑定
        channel.queueBind (QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY) ;
        //发送一条持久化的消息 ： hello wor l d !
        String message = "Hello World!";
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY,
                MessageProperties.PERSISTENT_TEXT_PLAIN ,
                message.getBytes());
        //关闭资源
        channel.close ();
        connection.close() ;
    }

    @Test
    public void consumer() throws Exception {
        Address[] addresses= new Address[] {new Address(IP_ADDRESS , PORT)};
        ConnectionFactory factory = new ConnectionFactory() ;
        factory.setUsername ("root") ;
        factory.setPassword ("root");
        //这里的连接方式与生产者的 demo 略有不同，注意辨别区别
        //创建连接
        Connection connection= factory.newConnection(addresses);
        //创建信道
        final Channel channel= connection.createChannel();
        //设置客户端最多接收未被 ack 的消息的个数
        channel.basicQos(64);
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("receive message:"+new String(body));
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        channel.basicConsume(QUEUE_NAME, consumer);
        //保持控制台状态
        System.in.read();
        channel.close();
        connection.close();
    }

}
