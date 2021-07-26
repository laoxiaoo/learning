package com.xiao.spi.impl;

import com.xiao.spi.Person;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.URL;

/**
 * @author xiao ji hao
 * @create 2021年07月26日 10:26:00
 */
@Slf4j
public class PersonImpl implements Person {
    @Override
    public void sayHello(URL url) {
        log.debug("==> person say hello");
    }
}
