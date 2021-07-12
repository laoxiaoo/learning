package com.xiao.tx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xiao ji hao
 * @create 2021年07月07日 21:57:00
 */
@Service
public class TxServiceImpl implements TxService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void insert() {
        jdbcTemplate.update("insert into student(name) values('张三2')");
        jdbcTemplate.update("insert into student(name) values('张三3')");
        throw new RuntimeException("测试异常");
    }
}
