package com.xiao.spi.impl;

import com.xiao.spi.Car;
import com.xiao.spi.Person;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.URL;

/**
 * 在meta-info/dubbo下面定义
 *
 * @author xiao ji hao
 * @create 2021年07月25日 23:14:00
 */
@Slf4j
public class CarImpl implements Car {
    /*private Person person;
    public void setPerson(Person person) {
        this.person = person;
    }*/
    @Override
    public void sayHello(URL url) {
        log.debug("==> car say hello");
        //person.sayHello(url);
    }
}
