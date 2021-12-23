package com.xiao.gof.decorator.order;

import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * @author xiao ji hao
 * @create 2021年10月23日 17:42:00
 */
@Component
public class VipMoneySum extends DecoratorMoneySum {
    @Override
    public void setMoney(Order order) {
        super.setMoney(order);

        //增强方法
        if(order.getMoney() >10) {
            order.setMoney(order.getMoney() - 20);
        }
    }
}
