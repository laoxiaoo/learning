package com.xiao.server.session;

/**
 * @author lao xiao
 * @create 2022年05月07日 21:40:00
 */
public class SessionFactory {

    private static Session session = new SessionMemoryImpl();

    public static Session getSession() {
        return session;
    }

}
