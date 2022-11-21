package com.xiao.util.chat.factory;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author lao xiao
 * @date 2022年10月28日 10:58
 */
public class SpringFactory implements ApplicationContextAware {

    static ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringFactory.applicationContext = applicationContext;
    }


    public static  <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }

    public static  <T> T getBean(String name, Class<T> clazz){
        return applicationContext.getBean(name, clazz);
    }
}
