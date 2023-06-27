package com.xiao.aop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lao xiao
 * @date 2023-05-04 15:10
 */
@Component
public class CircularBean {

    @Autowired
    private AopService aopService;

}
