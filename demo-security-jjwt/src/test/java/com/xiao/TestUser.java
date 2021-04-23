package com.xiao;

import com.xiao.entity.TUser;
import com.xiao.service.TUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName TestUser.java
 * @Description
 * @createTime 2021年01月23日 23:01:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TestUser {

    @Autowired
    private TUserService userService;
    @Test
    public void select() {
        List<TUser> list = userService.list();
        log.debug(list.toArray().toString());
    }
}
