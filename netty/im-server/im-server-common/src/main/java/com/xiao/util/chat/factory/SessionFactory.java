package com.xiao.util.chat.factory;

/**
 * @author lao xiao
 * @date 2022年10月19日 11:21
 */
public class SessionFactory {

    public static Session getMemorySession() {
        return SpringFactory.getBean("sessionMemory", Session.class);
    }


    public static Session getRedisSession() {
        return SpringFactory.getBean("sessionRedis", Session.class);
    }

}
