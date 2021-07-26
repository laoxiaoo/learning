package com.xiao.spi.aop;

import com.xiao.spi.Car;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.URL;

/**
 * @author xiao ji hao
 * @create 2021年07月26日 14:38:00
 */
@Slf4j
public class CarWrapper implements Car {

    private Car car;

    public CarWrapper(Car car) {
        //只有提供了一个带参的构造方法
        //这个类才会被认为是一个Wrapper类
        this.car = car;
    }
    @Override
    public void sayHello(URL url) {
        log.debug("==> wrapper say hello");
        car.sayHello(url);
    }
}
