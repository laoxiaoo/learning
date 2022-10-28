package com.xiao.transation;

import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @author lao xiao
 * @date 2022年10月11日 15:28
 */
@Component
public class AfterCommit {

    public void execute() {
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void afterCommit() {
                try {
                    //执行事物提交后的一些方法，可以是发送mq等一些
                } catch (Exception e) {

                }
            }
        });
    }

}
