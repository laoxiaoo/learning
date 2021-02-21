package com.xiao.in.spring.observer;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName ApplicationListenerDemo.java
 * @Description
 * @createTime 2021年02月21日 14:49:00
 */
public class ApplicationListenerDemo  {

    public static void main(String[] args) {
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        applicationContext.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent event) {
                System.out.println("收到事件:"+event);
            }
        });
        applicationContext.refresh();
        applicationContext.start();
        applicationContext.close();
    }
}
