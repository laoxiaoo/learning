package com.xiao.sqlSession;

/**
 * 生产sqlSession
 *
 * @author lao xiao
 * @create 2022年05月23日 20:35:00
 */
public interface SqlSessionFactory {

    SqlSession openSession();
}
