package com.xiao;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

/**
 * @author xiao ji hao
 * @create 2022年01月02日 12:08:00
 */
@SpringBootApplication
public class KafkaConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumerApplication.class, args);
        //simpleConsumer();
    }

    /**
     * 普通的客户端消费
     */
    public static void simpleConsumer() {
        Properties properties = new Properties();
        properties.put("key.deserializer", StringDeserializer.class.getName());
        properties.put("value.deserializer", StringDeserializer.class.getName());
        properties.put("bootstrap.servers", "192.168.1.132:9092");
        properties.put("group.id", "laoxiao");
        //是否自动提交
        properties.put("enable.auto.commit", "false");
        KafkaConsumer<Object, Object> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singleton("my_test"));
        while (true) {
            ConsumerRecords<Object, Object> records = consumer.poll(Duration.ofMillis(1000));
            for(ConsumerRecord record : records) {
                //此刻如果手动提交，则会重复消费
                System.out.println(record.value());
                consumer.commitAsync(new OffsetCommitCallback() {
                    @Override
                    public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
                        //提交的位移
                        System.out.println(offsets);
                    }
                });
            }
        }
    }

}
