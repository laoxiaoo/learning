package com.xiao.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.xiao.io.Resource;
import com.xiao.pojo.Configuration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * 用于解析sqlmapconf.xml这个住配置文件
 *
 * @author lao xiao
 * @create 2022年05月23日 20:36:00
 */
public class XmlConfigBuilder {

    /**
     * 解析配置文件
     * <br>
     * 获取datasource配置和 sql的配置信息
     * @param inputStream
     * @return
     */
    public Configuration parseConfig(InputStream inputStream) throws Exception {
        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();
        List<Element> dataSourceElement = rootElement.element("dataSource").elements();
        Properties properties = new Properties();
        //获取数据源配置信息集合
        dataSourceElement.forEach(var -> {
            properties.put(var.attributeValue("name"), var.attributeValue("value"));
        });
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(properties.getProperty("driver"));
        comboPooledDataSource.setJdbcUrl(properties.getProperty("url"));
        comboPooledDataSource.setUser(properties.getProperty("username"));
        comboPooledDataSource.setPassword(properties.getProperty("password"));
        Configuration configuration = new Configuration();
        configuration.setDataSource(comboPooledDataSource);

        List<Element> elements = rootElement.element("mappers").elements();
        elements.forEach(var -> {
            String mapper = var.attributeValue("resource");
            InputStream in = Resource.getResourceAsStream(mapper);
            XmlMapperBuilder xmlMapperBuilder = new XmlMapperBuilder(configuration);
            xmlMapperBuilder.parse(in);
        });
        return configuration;
    }

}
