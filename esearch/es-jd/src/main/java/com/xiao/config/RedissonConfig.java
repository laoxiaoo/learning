package com.xiao.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;

/**
 * @program: learning
 * @description: TODO
 * @author: lonely xiao
 * @create: 2020-10-13 23:53
 */
public class RedissonConfig {


    public RedissonClient redissonClient() {
        Config config = new Config();
        SingleServerConfig serverConfig =
                config.useSingleServer().setAddress("redis://192.168.1.134:6379").setDatabase(0);
        return Redisson.create(config);
    }
}