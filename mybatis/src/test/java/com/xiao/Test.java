package com.xiao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.reflection.ArrayUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @program: learning
 * @description: mybatis测试类
 * @author: lonely X
 * @create: 2020-10-22 22:55
 */
public class Test {
    String url;
    String username;
    String password;
    String sql;

    public void test1() throws Exception{
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1)+" "
                        +resultSet.getString(2));
            }
        } finally {
            resultSet.close();
            statement.close();
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
        BlogMapper blogMapper = (BlogMapper)Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[] {BlogMapper.class}, (proxy, method, args) -> {
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
}