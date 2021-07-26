package com.xiao.spi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

/**
 * @author xiao ji hao
 * @create 2021年07月26日 10:25:00
 */
@SPI
public interface Person {
    @Adaptive
    void sayHello(URL url);

}
