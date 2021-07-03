package com.xiao.aop.cglib;

/**
 * @author Malone Xiao
 * @create 2021年06月11日 23:25:00
 */
public class ProxyHelloService implements HelloService{

    private HelloService helloService;

    public ProxyHelloService(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    public void sayHello(String name) {
        System.out.println("xx 转发了 "+name+ "请求");
        helloService.sayHello(name);
    }
}
