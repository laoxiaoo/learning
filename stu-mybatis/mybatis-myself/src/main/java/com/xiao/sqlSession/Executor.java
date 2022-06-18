package com.xiao.sqlSession;

import com.xiao.pojo.Configuration;
import com.xiao.pojo.MapperStatement;

import java.util.Objects;

/**
 * 执行jdbc代码的接口
 *
 * @author lao xiao
 * @create 2022年05月29日 21:04:00
 */
public interface Executor {

    /**
     * 执行查询语句
     * @param configuration
     * @param mapperStatement
     * @param params
     * @return
     */
    Object query(Configuration configuration, MapperStatement mapperStatement, Object... params) throws Exception;

}
