package com.xiao.sqlSession;

import java.util.List;
import java.util.Objects;

/**
 * @author lao xiao
 * @create 2022年05月27日 08:07:00
 */
public class DefaultSqlSession implements SqlSession {

    @Override
    public <E> List<E> selectList(String statementId, Objects... param) {
        return null;
    }

}
