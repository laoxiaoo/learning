package com.xiao.pointcut;

import com.xiao.service.EchoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 定义一个xml，使用ProxyFactoryBean间接的代理EchoService
 * <br>
 * 当执行方法的时候，会执行MethodInterceptor的invoke方法
 *
 * @author xiao ji hao
 * @create 2022年02月24日 20:39:00
 */
public class XmlProxyFactoryBeanDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/proxy-factory-aop-context.xml");
        EchoService bean = (EchoService)context.getBean("echoServiceProxyFactoryBean");
        bean.sayHello("laoxiao");
        context.close();
    }

}
