package com.xiao.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.consumer.OffsetCommitCallback;
import org.apache.kafka.common.TopicPartition;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author xiao ji hao
 * @create 2022年01月02日 15:12:00
 */
@Component
@Slf4j
public class KafkaConsumerListener {


    @KafkaListener(topics = "my_test" , groupId = "laoxiao")
    public void consumerHandler(String msg , KafkaConsumer consumer) {
        log.info("消费数据：{}", msg);
        consumer.commitAsync(new OffsetCommitCallback() {
            @Override
            public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
                log.info(offsets.toString());
            }
        });
    }

}
