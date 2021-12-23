package com.xiao.aop.adapter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author xiao ji hao
 * @create 2021年10月21日 08:16:00
 */
@Service
@Slf4j
public class HelloServiceImpl implements HelloService {

    @Override
    public void sayHello() {
        log.info("say hello");
    }
}
