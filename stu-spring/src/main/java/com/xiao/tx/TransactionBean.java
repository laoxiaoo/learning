package com.xiao.tx;

import com.xiao.tx.service.TxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 测试spring 事务
 *
 * @author xiao ji hao
 * @create 2021年07月07日 21:37:00
 */
@ComponentScan(basePackages = "com.xiao.tx")
@EnableTransactionManagement
public class TransactionBean {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(TransactionBean.class);
        context.refresh();
        TxService txService = context.getBean(TxService.class);
        txService.insert();
        context.close();
    }
}
