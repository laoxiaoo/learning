package com.xiao.sqlSession;

import com.xiao.pojo.Configuration;

/**
 * 默认的实现
 *
 * @author lao xiao
 * @create 2022年05月27日 08:02:00
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession();
    }
}
