package com.xiao;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.util.IdUtil;
import com.xiao.dao.StudentMapper;
import com.xiao.pojo.Student;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.executor.BatchExecutor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.ReuseExecutor;
import org.apache.ibatis.executor.SimpleExecutor;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.ArrayUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.*;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransaction;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.ibatis.type.LocalDateTimeTypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Proxy;
import java.sql.*;
import java.util.List;

/**
 * @program: learning
 * @description: mybatis测试类
 * @author:
 * @create: 2020-10-22 22:55
 */
@Slf4j
public class Test {
    //String url = "jdbc:h2:mem:";
    String url = "jdbc:h2:database/h2db2";
    String username = "sa";
    String password = "123456";
    String sql;


    @org.junit.Test
    public void test1() throws Exception {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {

            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(url, username, password);
            ScriptRunner scriptRunner = new ScriptRunner(connection);
            scriptRunner.runScript(Resources.getResourceAsReader("create-table.sql"));
            statement = connection.createStatement();
            statement.execute("insert into student(name) values('张三')");
            resultSet = statement.executeQuery("select * from student");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " "
                        + resultSet.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //resultSet.close();
            //statement.close();
            connection.close();
        }
    }


    interface BlogMapper {
        @Select("SELECT name FROM blog WHERE id = #{id}")
        String selectBlog(int id);
    }


    @org.junit.Test
    public void testMybatisMapper() {
        //通过反射的方式，执行对应的sql
        BlogMapper blogMapper = (BlogMapper) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{BlogMapper.class}, (proxy, method, args) -> {
            Select select = method.getAnnotation(Select.class);
            String[] value = select.value();
            System.out.printf(ArrayUtil.toString(value));
            System.out.println(ArrayUtil.toString(args));
            return "执行结果";
        });
        String blog = blogMapper.selectBlog(1);
        System.out.println(blog);
    }

    public void testConnect() {
    }

    public void testMateObj() {
    }

    @org.junit.Test
    public void initMybatis() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Configuration configuration = sqlSession.getConfiguration();
        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
        typeHandlerRegistry.register(LocalDateTimeTypeHandler.class);
        //获取statement, 里面包含执行的xml，接口等信息
        MappedStatement mappedStatement = configuration.getMappedStatement("com.xiao.dao.StudentMapper.selectStudent");
        //获取执行器
        JdbcTransaction jdbcTransaction = new JdbcTransaction(sqlSession.getConnection());
        ReuseExecutor executor = new ReuseExecutor(configuration, jdbcTransaction);
        //Executor executor = configuration.newExecutor(new JdbcTransaction(sqlSession.getConnection()), ExecutorType.REUSE);
        List<Object> query = executor.query(mappedStatement, 2, RowBounds.DEFAULT, Executor.NO_RESULT_HANDLER);
        log.debug(cn.hutool.core.util.ArrayUtil.toString(query));
        List<Object> query2 = executor.query(mappedStatement, 2, RowBounds.DEFAULT, Executor.NO_RESULT_HANDLER);
        log.debug(cn.hutool.core.util.ArrayUtil.toString(query2));


    }


    @org.junit.Test
    public void selectStudent() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        Student student = studentMapper.selectStudent(2l);
        log.debug(student.toString());
    }

    /**
     * 测试雪花算法
     */
    @org.junit.Test
    public void snowFlack() {
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        long id = snowflake.nextId();
        System.out.println(id);
        System.out.println(snowflake.nextId());
        System.out.println(snowflake.nextId());
        System.out.println(snowflake.nextId());
        System.out.println(snowflake.nextId());
        System.out.println(snowflake.nextId());
        System.out.println(snowflake.nextId());
        System.out.println(snowflake.nextId());

    }

}