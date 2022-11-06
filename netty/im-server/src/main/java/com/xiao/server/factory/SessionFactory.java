package com.xiao.server.factory;

import cn.hutool.extra.spring.SpringUtil;

/**
 * @author lao xiao
 * @date 2022年10月19日 11:21
 */
public class SessionFactory {

    public static Session getMemorySession() {
        return SpringFactory.getBean("sessionMemoryImpl", Session.class);
    }

}
