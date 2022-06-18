package com.xiao.config;

import com.xiao.pojo.Configuration;
import com.xiao.pojo.MapperStatement;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * 用于解析装载sql的mapper文件
 *
 * @author lao xiao
 * @create 2022年05月27日 07:30:00
 */
public class XmlMapperBuilder {

    private Configuration configuration;

    public XmlMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * 解析单个的xml mapper文件
     * @param inputStream
     */
    public void parse(InputStream inputStream){
        try {
            Document document = new SAXReader().read(inputStream);
            Element rootElement = document.getRootElement();
            List<Element> elements = rootElement.elements();
            String namespace = rootElement.attributeValue("namespace");
            elements.forEach(var -> {
                MapperStatement mapperStatement = new MapperStatement();
                mapperStatement.setId(var.attributeValue("id"));
                mapperStatement.setParamType(var.attributeValue("paramType"));
                mapperStatement.setResultType(var.attributeValue("resultType"));
                mapperStatement.setSql(var.getTextTrim());
                String namespaceTmp = namespace;
                configuration.getMapperStatementMap()
                        .put(namespaceTmp+ "."+var.attributeValue("id"), mapperStatement);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
