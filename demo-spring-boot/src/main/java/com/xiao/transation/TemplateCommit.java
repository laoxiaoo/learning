package com.xiao.transation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author lao xiao
 * @date 2022年10月11日 15:26
 */
@Component
public class TemplateCommit {

    @Autowired
    private TransactionTemplate transactionTemplate;

    public void execute() {
        transactionTemplate.execute(status -> {
            //执行同一个事物的方法
            return Boolean.TRUE;
        });
    }

}
