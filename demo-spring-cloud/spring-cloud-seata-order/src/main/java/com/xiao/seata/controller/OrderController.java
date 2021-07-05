package com.xiao.seata.controller;


import com.xiao.seata.entity.TOrder;
import com.xiao.seata.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order/create")
    public void create(TOrder order){
        orderService.create(order);
    }
}
