package com.xiao.service;

import com.xiao.ao.UserAO;
import org.springframework.stereotype.Service;

/**
 * @author lao xiao
 * @create 2022年05月29日 15:53:00
 */
@Service
public class HelloServiceImpl {

    public String hello(UserAO userAO) {
        return userAO.getName();
    }

}
