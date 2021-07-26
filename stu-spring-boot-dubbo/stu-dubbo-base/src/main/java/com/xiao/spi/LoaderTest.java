package com.xiao.spi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;

/**
 * @author xiao ji hao
 * @create 2021年07月25日 23:21:00
 */
public class LoaderTest {

    public static void main(String[] args) {
        ExtensionLoader<Car> loader = ExtensionLoader.getExtensionLoader(Car.class);
        /*URL url = new URL("http", "localhost", 8080);
        url = url.addParameter("person", "person");*/
        Car car = loader.getExtension("car");
        car.sayHello(null);
    }
}
