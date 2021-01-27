package com.xiao.config;

import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Indexed;

import javax.annotation.PostConstruct;
import java.text.DateFormat;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName InitConfig.java
 * @Description
 * @createTime 2021年01月24日 19:37:00
 */
@Component
public class InitConfig {


    @PostConstruct
    public void init() {
    }
}
