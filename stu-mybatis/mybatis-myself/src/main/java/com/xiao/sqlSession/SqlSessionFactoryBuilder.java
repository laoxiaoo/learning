package com.xiao.sqlSession;

import com.xiao.config.XmlConfigBuilder;
import com.xiao.pojo.Configuration;

import java.io.InputStream;

/**
 * @author lao xiao
 * @create 2022年05月23日 20:36:00
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream inputStream) throws Exception {
        //解析出对应的config来
        XmlConfigBuilder xmlConfigBuilder = new XmlConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parseConfig(inputStream);

        //获取 SqlSessionFactory
        return new DefaultSqlSessionFactory(configuration);
    }

}
