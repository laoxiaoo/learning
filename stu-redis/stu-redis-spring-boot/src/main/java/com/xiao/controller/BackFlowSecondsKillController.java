package com.xiao.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * 采用库存回流的方式进行秒杀
 *
 * 风险点：如果并发太大，导致redis连接超时，可能库存回流会有问题
 *
 * 解决办法：并发不是很高很高，可以采用限流来进行控制，这种方式也是最简单的
 *
 * @author lao xiao
 * @date 2022年11月16日 15:16
 */
@RestController
@RequestMapping("/backFlow")
@Slf4j
public class BackFlowSecondsKillController {

    @Autowired
    private RedisTemplate redisTemplate;

    private final static String STOCK_KEY = "px:inventory";


    @PostMapping("/secondsKill")
    public Map secondsKill() {
        String user = IdUtil.simpleUUID();
        Long stock = redisTemplate.opsForValue().decrement(STOCK_KEY);
        if(stock < 0) {
            redisTemplate.opsForValue().increment(STOCK_KEY);
            log.debug("库存不足：{}", stock);
            return Collections.singletonMap("data", "ERROR");
        }
        try{
            log.debug("当前库存:{}", stock);
            //创建秒杀订单等相关操作
            redisTemplate.opsForSet().add("px:user", user);
        } catch (Exception e) {
            redisTemplate.opsForValue().increment(STOCK_KEY);
        }
        return Collections.singletonMap("data", "OK");
    }

}
