package com.xiao.controller;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import static com.xiao.controller.ErrorSecondsKillController.getUserNumber;
import static com.xiao.controller.ErrorSecondsKillController.sendUserNumber;

/**
 *
 * lua 版本的秒杀，这个版本不会产生库存问题
 *
 * @author xiao ji hao
 * @create 2021年06月25日 15:21:00
 */
@RestController
@RequestMapping("/lua")
@Slf4j
public class LuaSecondsKillController {

    @Autowired
    private RedisTemplate redisTemplate;


    @PostMapping("/secondsKill")
    public void secondsKill() {
        sendUserNumber.incrementAndGet();
        String userId = IdUtil.simpleUUID();
        String lua = "local num = redis.call('get', KEYS[1])\n" +
                "if tonumber(num) <= 0 then\n" +
                "\treturn -1\n" +
                "else\n" +
                "\tredis.call('decr', KEYS[1])\n" +
                "redis.call(\"sadd\", KEYS[2], KEYS[3])\n" +
                "\treturn 1\n" +
                "end";
        Object obj = redisTemplate.execute((RedisCallback) (connection) -> {
            return connection.eval(lua.getBytes(StandardCharsets.UTF_8), ReturnType.MULTI,
                    3,
                    "px:inventory".getBytes(StandardCharsets.UTF_8),
                    "px:user".getBytes(StandardCharsets.UTF_8),
                    userId.getBytes(StandardCharsets.UTF_8));
        });
        //发送mq消息，转入订单流程

        //log.debug("==> redis call {}", obj);
        Optional.ofNullable(obj).map(var -> (List<Long>) var)
                .filter(var -> var.size()>0)
                .map(var -> var.get(0))
                .ifPresent(var -> {
                    if(var.compareTo(1L) == 0) {
                        getUserNumber.incrementAndGet();
                    }
                });

    }

}
