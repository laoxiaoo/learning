package com.xiao.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 一个简单的redis锁
 *
 * <ul>
 *     没有一点价值，只是帮助理解redis分布式锁的底层原理
 * <p>
 *
 *
 * @author xiao ji hao
 * @create 2021年06月30日 13:35:00
 */
@Component
public class Lock {

    @Autowired
    public RedisTemplate redisTemplate;

    public static final String KEY = "lock:key";

    /**
     * 加锁操作，value可以添加自己的线程 <br/>
     * 目的是为了能够在将来，防止其他线程删除本线程的锁
     */
    public  String lock() {
        String value = UUID.randomUUID().toString()+Thread.currentThread().getId();
        Boolean bool = redisTemplate.opsForValue().setIfAbsent(KEY, value, 60L, TimeUnit.SECONDS);
        if(bool) {
            return value;
        } else {
            return "";
        }

    }

    public void  unlock(String value) {
        String script = "local value = redis.call('get', KEYS[1])\n" +
                "if value == KEYS[2] then\n" +
                "\tredis.call('del', KEYS[1])\n" +
                "\treturn 1\n" +
                "else \n" +
                "\treturn 0 end";
        Object res = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.eval(script.getBytes(StandardCharsets.UTF_8), ReturnType.INTEGER, 2, KEY.getBytes(StandardCharsets.UTF_8), value.getBytes(StandardCharsets.UTF_8));
            }
        });
        System.out.println(res);
    }

}
