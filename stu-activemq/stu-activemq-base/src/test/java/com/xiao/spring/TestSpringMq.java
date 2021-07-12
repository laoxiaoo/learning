package com.xiao.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author malone xiao
 * @ClassName TestSpringMq.java
 * @Description
 * @createTime 2021年05月25日 16:05:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TestSpringMq {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Test
    public void springProvider() {
        jmsTemplate.send(session -> session.createTextMessage("spring-text"));
    }

    @Test
    @JmsListener(destination = "")
    public void springConsumer() {
        System.out.println(jmsTemplate.receiveAndConvert());;
    }

}
