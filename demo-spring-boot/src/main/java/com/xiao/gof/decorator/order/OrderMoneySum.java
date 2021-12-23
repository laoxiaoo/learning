package com.xiao.gof.decorator.order;

/**
 * @author xiao ji hao
 * @create 2021年10月23日 13:44:00
 */
public class OrderMoneySum implements MoneySum {
    @Override
    public void setMoney(Order order) {
        // 获得商品信息
        //模拟商品价格
        int price = 10;

        order.setMoney(10);
    }
}
