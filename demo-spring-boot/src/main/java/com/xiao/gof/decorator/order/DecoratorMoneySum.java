package com.xiao.gof.decorator.order;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 适配器类，一般为了拓展，使用抽象类
 *
 * @author xiao ji hao
 * @create 2021年10月23日 17:23:00
 */
public abstract class DecoratorMoneySum implements MoneySum{


    protected MoneySum moneySum;

    public void setMoneySum(MoneySum moneySum) {
        this.moneySum = moneySum;
    }

    @Override
    public void setMoney(Order order) {
        moneySum.setMoney(order);
    }
}
