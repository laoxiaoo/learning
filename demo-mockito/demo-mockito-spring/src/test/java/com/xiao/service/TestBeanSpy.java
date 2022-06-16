package com.xiao.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xiao ji hao
 * @create 2022年01月09日 22:46:00
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestBeanSpy {

    @Spy
    private UserManager userManager = new UserManagerSpy();


    //如果使用InjectMocks，则 @Mock的对象会注入属性中
    @InjectMocks
    @Autowired
    private UserServiceImpl userService;

    //@MockBean
    //private UserManagerImpl userManagerImpl;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test() {
        System.out.println(userService.getUser());
    }

}
