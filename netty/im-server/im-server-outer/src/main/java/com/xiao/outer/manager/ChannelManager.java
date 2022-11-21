package com.xiao.outer.manager;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lao xiao
 * @date 2022年09月30日 17:49
 */
public class ChannelManager {
    //存储用户的channel，
    private static Map<String, Channel> userChannel = new ConcurrentHashMap<String, Channel>();


}
