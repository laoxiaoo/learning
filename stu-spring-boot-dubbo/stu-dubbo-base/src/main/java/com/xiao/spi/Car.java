package com.xiao.spi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.SPI;

/**
 *
 * @author xiao ji hao
 * @create 2021年07月25日 23:13:00
 */
@SPI
public interface Car {

    void sayHello(URL url);
}
