package com.xiao;

import com.xiao.base.BaseCode;
import com.xiao.dao.StudentMapper;
import com.xiao.pojo.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * 参数处理器 测试
 *
 * @author  xiao ji hao
 */
public class ParamTest extends BaseCode {



    @Test
    public void test2() {
        studentMapper.select(new Student(3l, "zhangsan","1"));
    }


}
