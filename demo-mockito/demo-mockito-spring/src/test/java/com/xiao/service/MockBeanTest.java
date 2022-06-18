package com.xiao.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xiao jie
 * @create 2022年06月16日 20:06:00
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MockBeanTest {
    @Autowired
    private UserServiceImpl userService;


    @SpyBean
    private DeepService deepService;

    @Test
    public void getUserInfo() {
        Mockito.when(deepService.getDeep(Mockito.any())).then(var -> "test spy");
        System.out.println(userService.getUser());
    }
}
