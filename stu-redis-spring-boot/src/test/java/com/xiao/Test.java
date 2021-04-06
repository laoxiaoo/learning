package com.xiao;

import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

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

    @org.junit.Test
    public void test1() {
        System.out.println(redisTemplate);
    }
}
