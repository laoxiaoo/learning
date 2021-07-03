package com.xiao;

import com.xiao.lock.Lock;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Stream;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName Test.java
 * @Description
 * @createTime 2021年03月25日 21:39:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Test {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private Lock lock;

    @org.junit.Test
    public void test1() {
        Stream.iterate(1, x->1).limit(10).forEach(var -> {
            new Thread(() -> {
                String value = this.lock.lock();
                if(value == "") {
                    return;
                }
                try {
                    log.debug("线程执行...");
                    sleep(1000l);
                } finally {
                    lock.unlock(value);
                }
            }).start();
        });

        sleep(10000l);
    }


    public void sleep(Long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
