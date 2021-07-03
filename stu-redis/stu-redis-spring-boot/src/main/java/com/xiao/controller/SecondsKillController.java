package com.xiao.controller;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * 秒杀测试controller
 * 该例子采用redis 乐观锁
 * 故会产生 库存问题
 * 另外：获取库存数和乐观锁里面的代码不是原子操作，会有并发问题
 *
 * @author xiao ji hao
 * @create 2021年06月25日 12:02:00
 */
@RestController
@Slf4j
public class SecondsKillController {

    @Autowired
    private RedisTemplate redisTemplate;

    public static volatile AtomicInteger getUserNumber = new AtomicInteger();

    public static volatile AtomicInteger sendUserNumber = new AtomicInteger();

    @PostMapping("/secondsKill")
    public void secondsKill() throws InterruptedException {
        sendUserNumber.incrementAndGet();
        String userId = IdUtil.simpleUUID();
        redisTemplate.setEnableTransactionSupport(true);
        long inventory = Long.parseLong(redisTemplate.opsForValue().get("px:inventory").toString());
        if(inventory <= 0) {
            //log.debug("==>库存不够....");
            return;
        }
        log.debug("==>库存剩余：{}", inventory);
        redisTemplate.watch("px:inventory");
        redisTemplate.multi();
        try {
            redisTemplate.opsForValue().decrement("px:inventory");
        } finally {
            List list = redisTemplate.exec();
            Optional.ofNullable(list).filter(var -> var.size()>0).ifPresent( var -> {
                log.debug("用户 {} 抢到商品", userId);
                getUserNumber.incrementAndGet();
                redisTemplate.opsForSet().add("px:user", userId);
            });
        }
    }

    @GetMapping("/getNumber")
    public void getNumber() {
        log.debug("申请抢购： {}", sendUserNumber);
        log.debug("抢购成功： {}", getUserNumber);

        log.debug("库存： {}", redisTemplate.opsForValue().get("px:inventory"));
        log.debug("用户大小： {}", redisTemplate.opsForSet().size("px:user"));
    }

    @PostMapping("/clear")
    public void clear() {
        sendUserNumber.set(0);
        getUserNumber.set(0);
        redisTemplate.delete("px:user");
        //redisTemplate.opsForValue().set("px:inventory", 1);
        redisTemplate.execute((RedisCallback) connection ->
                connection.eval("redis.call('set', 'px:inventory', 200)".getBytes(StandardCharsets.UTF_8),
                        ReturnType.MULTI, 0));
    }

}
