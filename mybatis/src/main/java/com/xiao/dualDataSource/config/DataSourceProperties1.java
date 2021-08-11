package com.xiao.dualDataSource.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.sql.DataSource;

/**
 * @author xiao ji hao
 * @create 2021年08月11日 21:04:00
 */
@ConfigurationProperties(prefix = "spring.datasource.test1")
@Getter
@Setter
public class DataSourceProperties1 {

    private ClassLoader classLoader;

    /**
     * Name of the datasource. Default to "testdb" when using an embedded database.
     */
    private String name;

    /**
     * Whether to generate a random datasource name.
     */
    private boolean generateUniqueName = true;

    /**
     * Fully qualified name of the connection pool implementation to use. By default, it
     * is auto-detected from the classpath.
     */
    private Class<? extends DataSource> type;

    /**
     * Fully qualified name of the JDBC driver. Auto-detected based on the URL by default.
     */
    private String driverClassName;

    /**
     * JDBC URL of the database.
     */
    private String url;

    /**
     * Login username of the database.
     */
    private String username;

    /**
     * Login password of the database.
     */
    private String password;

    /**
     * JNDI location of the datasource. Class, url, username and password are ignored when
     * set.
     */
    private String jndiName;

}
