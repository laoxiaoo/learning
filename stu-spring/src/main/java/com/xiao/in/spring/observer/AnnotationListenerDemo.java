package com.xiao.in.spring.observer;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

/**
 * @author lonely xiao
 * @version 1.0
 * @ClassName AnnotationListenerDemo.java
 * @Description
 * @createTime 2021年02月21日 15:13:00
 */
public class AnnotationListenerDemo implements ApplicationEventPublisherAware {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationListenerDemo.class);
        applicationContext.addApplicationListener(new ApplicationListener(){
            @Override
            public void onApplicationEvent(ApplicationEvent event) {
                System.out.println("收到注入事件："+event);
            }
        });

        applicationContext.refresh();

        applicationContext.publishEvent(new ApplicationEvent("hello event") {
        });
        applicationContext.close();
    }

    @EventListener
    public void commonEvent(ApplicationEvent event) {
        System.out.println("事件:"+event);
    }

    @EventListener
    public void refreshEvent(ContextRefreshedEvent event) {
        System.out.println("refresh事件:"+event);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        applicationEventPublisher.publishEvent(new ApplicationEvent("发布事件") {
        });
    }
}
