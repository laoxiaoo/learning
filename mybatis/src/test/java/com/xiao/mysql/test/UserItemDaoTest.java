package com.xiao.mysql.test;

import com.xiao.mysql.lock.UserItemDao;
import com.xiao.mysql.lock.UserItemService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xiao ji hao
 * @create 2021年08月30日 07:55:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserItemDaoTest {

    @Autowired
    private UserItemService userItemService;

    @Test
    public void test1() throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(100);
        for(int i=0; i<1000; i++) {
            threadPool.execute(() -> {
                userItemService.update();
            });
        }
        Thread.sleep(1000*60);
    }

}
