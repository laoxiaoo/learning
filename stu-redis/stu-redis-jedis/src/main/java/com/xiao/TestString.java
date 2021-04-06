package com.xiao;

import redis.clients.jedis.Jedis;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName TestString.java
 * @Description
 * @createTime 2021年03月25日 21:03:00
 */
public class TestString {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.1.131", 6379);
        System.out.println(jedis.get("name"));
        jedis.close();
    }

}
