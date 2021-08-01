package com.xiao.base;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;

/**
 * 测试的基类，存放一些常量，初始化一些连接等
 *
 * @author xiao ji hao
 * @create 2021年07月31日 17:34:00
 */
public class ProductBase {
    /**
     * 主题
     */
    public static final String TOPIC = "my-topic";
    /**
     * 地址
     */
    public static final String ADDRESS = "192.168.1.131:9876";

    /**
     * 组名
     */
    protected static final String GROUP_NAME = "laoxiao";


    protected static DefaultMQProducer producer;

    static {
        producer = new DefaultMQProducer(GROUP_NAME);
        producer.setNamesrvAddr(ADDRESS);
        try {
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }


}
