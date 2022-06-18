package com.xiao.sqlSession;

import java.util.List;
import java.util.Objects;

/**
 * 会话管理接口:执行Executor的接口方法，将返回值封装成对象返回
 *
 * @author lao xiao
 * @create 2022年05月27日 08:05:00
 */
public interface SqlSession {

    /**
     * 查询列表
     * @param statementId 就是map的key, 也就是namespace.id
     * @param param
     * @param <E>
     * @return
     */
    <E> List<E> selectList(String statementId, Objects ... param);

}
