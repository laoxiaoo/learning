package com.xiao.gof.decorator.order;

import org.springframework.stereotype.Component;

/**
 * @author xiao ji hao
 * @create 2021年10月23日 17:28:00
 */
@Component
public class FullMoneySum extends DecoratorMoneySum {



    @Override
    public void setMoney(Order order) {
        super.setMoney(order);
        //增强方法
        //满10块-1块
        if(order.getMoney() > 10) {
            order.setMoney(order.getMoney()-10);
        }
    }
}
