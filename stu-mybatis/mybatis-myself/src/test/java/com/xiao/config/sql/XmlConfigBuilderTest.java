package com.xiao.config.sql;

import com.xiao.config.XmlConfigBuilder;
import com.xiao.io.Resource;
import com.xiao.pojo.Configuration;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;

/**
 * @author lao xiao
 * @create 2022年05月24日 20:33:00
 */
public class XmlConfigBuilderTest {

    @Test
    public void test1() throws Exception {
        XmlConfigBuilder xmlConfigBuilder = new XmlConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parseConfig(Resource.getResourceAsStream("sqlMapConfig.xml"));
        System.out.println(configuration);
    }

}
