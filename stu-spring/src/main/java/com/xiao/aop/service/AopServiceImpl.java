package com.xiao.aop.service;

import org.springframework.stereotype.Service;

/**
 * @author xiao ji hao
 * @create 2021年07月07日 15:51:00
 */
@Service
public class AopServiceImpl implements AopService {
    @Override
    public void sayHello() {
        System.out.println("hello");
    }
}
