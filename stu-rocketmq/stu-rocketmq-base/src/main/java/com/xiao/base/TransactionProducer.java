package com.xiao.base;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 事务消息
 *
 * @author xiao ji hao
 * @create 2021年08月01日 15:07:00
 */
@Slf4j
public class TransactionProducer {

    public static void main(String[] args) throws Exception {
        TransactionMQProducer txProducer = new TransactionMQProducer("tx");
        txProducer.setNamesrvAddr(ProductBase.ADDRESS);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 1000l, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));
        //为生产者指定线程池
        txProducer.setExecutorService(executor);
        txProducer.setTransactionListener(new ICBCTransactionListener());
        txProducer.start();

        Message message = new Message("tx-topic", "taga", "tx".getBytes(StandardCharsets.UTF_8));
        //消息， 本地事务使用的业务参数
        TransactionSendResult sendResult = txProducer.sendMessageInTransaction(message, null);
        log.debug("启动事务生产者： {}", sendResult);
    }

    static class ICBCTransactionListener implements TransactionListener {
        @Override
        public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
            //当消息投递到mq后，处于半提交状态
            // 执行本地事务
            return LocalTransactionState.UNKNOW;
        }

        @Override
        public LocalTransactionState checkLocalTransaction(MessageExt msg) {
            //回查:只有以下两种情况会回查
            //1.回调操作返回UNKNWON
            //2.TC没有接收到TM的最终全局事务确认指令
            log.debug("收到回查消息：{}", msg);
            return LocalTransactionState.COMMIT_MESSAGE;
        }
    }

}
