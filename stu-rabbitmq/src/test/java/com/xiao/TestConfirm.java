package com.xiao;

import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.MessageProperties;
import com.xiao.faction.CommonChannelFactory;
import org.junit.Test;

import java.io.IOException;

import static com.xiao.faction.CommonChannelFactory.EXCHANGE_NAME;
import static com.xiao.faction.CommonChannelFactory.ROUTING_KEY;

/**
 * 测试 消息确认
 *
 * @author malone xiao
 * @ClassName TestConfirm.java
 * @Description
 * @createTime 2021年06月04日 11:45:00
 */
public class TestConfirm {

    /**
     * 串行同步等待的方式获取消息的投递结果
     */
    @Test
    public void pubConfirm() throws Exception {

        CommonChannelFactory commonChannelFactory = new CommonChannelFactory();
        commonChannelFactory.execution(channel -> {
            try {
                channel.confirmSelect();
                channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY,
                        MessageProperties.PERSISTENT_TEXT_PLAIN,
                        "confirm.....".getBytes());
                if(!channel.waitForConfirms()) {
                    System.out.println("消息未投递成功.....");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     *
     */
    @Test
    public void pubListenerConfirm() throws Exception {
        CommonChannelFactory commonChannelFactory = new CommonChannelFactory();
        commonChannelFactory.execution(channel -> {
            try {
                channel.confirmSelect();
                channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY,
                        MessageProperties.PERSISTENT_TEXT_PLAIN,
                        "confirm.....".getBytes());
                channel.addConfirmListener(new ConfirmListener() {
                    @Override
                    public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                        System.out.println("ack ---"+deliveryTag+"----"+multiple);
                    }

                    @Override
                    public void handleNack(long deliveryTag, boolean multiple) throws IOException {

                    }
                });
                System.in.read();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
