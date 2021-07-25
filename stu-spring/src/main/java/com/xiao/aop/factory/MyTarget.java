package com.xiao.aop.factory;

import lombok.extern.slf4j.Slf4j;

/**
 * 定义一个目标拦截类
 *
 * @author xiao ji hao
 */
@Slf4j
public class MyTarget {

    public void sayHello() {
        log.debug("==> hello");
    }
}
