package com.xiao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import javax.websocket.server.PathParam;

/**
 * @author xiao ji hao
 * @create 2021年07月06日 20:08:00
 */
@RestController
public class TestController {

    @Autowired
    private JmsMessagingTemplate template;

    @Autowired
    private Queue queue;

    @PostMapping("/sendMessage")
    public void sendMessage(@PathParam("ms") String ms) {
        template.convertAndSend(queue, ms);
    }

}
