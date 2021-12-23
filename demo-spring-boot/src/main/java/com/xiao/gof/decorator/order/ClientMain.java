package com.xiao.gof.decorator.order;

import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * 模拟用户调用
 * @author xiao ji hao
 * @create 2021年10月23日 18:03:00
 */
public class ClientMain {

    @Autowired
    private VipMoneySum vipMoneySum;

    @Autowired
    private FullMoneySum fullMoneySum;


}
