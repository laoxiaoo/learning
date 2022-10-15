package com.xiao.dualDataSource.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;

/**
 * @author xiao ji hao
 * @create 2021年08月11日 20:17:00
 */
@Configuration
@MapperScan(basePackages = "com.xiao.dualDataSource.dao1", sqlSessionFactoryRef = "sqlSessionFactory1")
@EnableConfigurationProperties(DataSourceProperties1.class)
public class DataSourceConfig1 {

    @Bean("datasource1")
    @Primary
    public DataSource getDatasource1(DataSourceProperties1 dataSourceProperties) {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setPassword(dataSourceProperties.getPassword());
        hikariDataSource.setUsername(dataSourceProperties.getUsername());
        hikariDataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        hikariDataSource.setJdbcUrl(dataSourceProperties.getUrl());
        return hikariDataSource;
    }

    @Bean("sqlSessionFactory1")
    @Primary
    public SqlSessionFactory getSqlSessionFactory1(@Qualifier("datasource1") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(
                // 设置mybatis的xml所在位置
                new PathMatchingResourcePatternResolver()
                        .getResources("classpath*:dao/test01/*.xml"));
        SqlSessionFactory sqlSessionFactory = bean.getObject();
        sqlSessionFactory.getConfiguration().setDefaultEnumTypeHandler(AutoEnumTypeHandler.class);
        return sqlSessionFactory ;
    }


    @Bean
    @Primary
    public TransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

}
