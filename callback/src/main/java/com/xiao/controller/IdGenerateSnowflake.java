package com.xiao.controller;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class IdGenerateSnowflake {
    /**
     * 范围 0-31
     * 可以理解成 机房中 具体的机器
     */
    private long workerId;
    /**
     * 范围 0-31
     * 数据中心   可以理解成 机房
     */
    private long datacenterId = 1L;

    /**
     * 雪花算法生成器
     */
    private Snowflake snowflake;

    /**
     * 初始化
     */
    @PostConstruct
    public void init() {
        try {
            //1号机房 1号机器
            //workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
            String ipv4 = NetUtil.getLocalhostStr();
            Long ipLong = Long.parseLong(ipv4.replaceAll("\\.", ""));
            workerId = ipLong.hashCode() % 32;
            snowflake = IdUtil.createSnowflake(workerId, datacenterId);
        } catch (Exception e) {

        }
    }

    public long getId(){
        System.out.println(snowflake);
        return  snowflake.nextId();
    }


}