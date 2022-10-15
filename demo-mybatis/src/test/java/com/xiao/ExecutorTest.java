package com.xiao;

import com.xiao.pojo.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * mybatis执行器测试
 *
 * @creator xiao ji hao
 */
@Slf4j
public class ExecutorTest {
    SqlSessionFactory sqlSessionFactory;

    @Before
    public void before() throws Exception{
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void session() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Object o = sqlSession.selectOne("com.xiao.dao.StudentMapper.selectStudent", 1);
        System.out.println(o);
    }

    /**
     * config的方式 使用缓存
     */
    @Test
    public void cache() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Configuration configuration = sqlSession.getConfiguration();

        Cache cache = configuration.getCache("com.xiao.dao.StudentMapper");
         cache.putObject("laoxiao", new Student());

        cache.getObject("laoxiao");
    }

}
