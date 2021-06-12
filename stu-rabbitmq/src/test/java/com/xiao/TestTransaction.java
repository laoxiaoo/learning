package com.xiao;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import com.xiao.faction.CommonChannelFactory;
import org.junit.Test;

import java.io.IOException;

import static com.xiao.faction.CommonChannelFactory.EXCHANGE_NAME;
import static com.xiao.faction.CommonChannelFactory.ROUTING_KEY;

/**
 * @author malone xiao
 * @ClassName TestTransaction.java
 * @createTime 2021年06月04日 10:15:00
 */
public class TestTransaction {

    @Test
    public void commit() throws Exception {
        CommonChannelFactory commonChannelFactory = new CommonChannelFactory();
        commonChannelFactory.execution(channel -> {
            try {
                channel.txSelect();
                channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY,
                        MessageProperties.PERSISTENT_TEXT_PLAIN,
                        "commit.....".getBytes());
                //int i = 1 / 0;
                channel.txCommit();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    channel.txRollback();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
