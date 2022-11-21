package com.xiao.util.chat.factory;

import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lao xiao
 * @create 2022年05月07日 21:40:00
 */
public class SessionMemoryImpl implements Session{

    /**
     * 存储当前的会话信息
     */
    private final Map<String, Channel> usernameChannelMap = new ConcurrentHashMap<>();
    private final Map<Channel, String> channelUsernameMap = new ConcurrentHashMap<>();
    private final Map<Channel, Map<String, Object>> channelAttributesMap = new ConcurrentHashMap<>();

    @Override
    public void bind(Channel channel, String username) {
        usernameChannelMap.put(username, channel);
        channelUsernameMap.put(channel, username);
        channelAttributesMap.put(channel, new ConcurrentHashMap<>());
    }

    @Override
    public Channel getChannel(String userName) {
        return usernameChannelMap.get(userName);
    }

    @Override
    public void removeChannel(Channel channel) {

    }


}
