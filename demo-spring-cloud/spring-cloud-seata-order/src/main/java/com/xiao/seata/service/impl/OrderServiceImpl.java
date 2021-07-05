package com.xiao.seata.service.impl;

import com.xiao.seata.entity.TOrder;
import com.xiao.seata.dao.OrderDao;
import com.xiao.seata.service.AccountService;
import com.xiao.seata.service.OrderService;
import com.xiao.seata.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private StorageService storageService;

    @Autowired
    private AccountService accountService;

    @GlobalTransactional
    public void create(TOrder order){
        System.out.println("开始创建订单");
        orderDao.insert(order);
        System.out.println("开始减库存");
        storageService.decrease(order.getProductId(), 1);
        System.out.println("开始减金额");
        accountService.decrease(order.getUserId(), order.getMoney());
        System.out.println("开始修改订单状态");
        orderDao.updateStatus(order.getId());
    }
}
