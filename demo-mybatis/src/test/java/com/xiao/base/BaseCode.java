package com.xiao.base;

import com.xiao.dao.StudentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

public class BaseCode {

    protected SqlSessionFactory sqlSessionFactory;

    protected SqlSession sqlSession;

    protected StudentMapper studentMapper;

    @Before
    public void before() throws Exception{
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession();

        studentMapper = sqlSession.getMapper(StudentMapper.class);
    }

    @After
    public void after() {
        sqlSession.close();
    }

    @Test
    public void test() {
        studentMapper.selectStudent(1L);
    }

}
