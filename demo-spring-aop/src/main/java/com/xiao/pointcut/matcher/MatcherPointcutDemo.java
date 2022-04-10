package com.xiao.pointcut.matcher;

import com.xiao.service.DefaultEchoServiceImpl;
import com.xiao.service.EchoService;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * 可以看到，当StaticMethodMatcherPointcut的实现方法返回true时
 * <br>
 * 则会执行EchoMethodServiceInterceptor拦截方法
 *
 * @author xiao ji hao
 * @create 2022年02月15日 22:42:00
 */
public class MatcherPointcutDemo {

    public static void main(String[] args) {
        EchoMatcherService pointcut = new EchoMatcherService();
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new EchoMethodServiceInterceptor());
        DefaultEchoServiceImpl defaultEchoService = new DefaultEchoServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(defaultEchoService);
        proxyFactory.addAdvisor(advisor);
        EchoService echoService = (EchoService) proxyFactory.getProxy();
        echoService.sayHello("laoxiao");
    }

}
