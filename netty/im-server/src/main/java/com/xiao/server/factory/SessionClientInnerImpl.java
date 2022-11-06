package com.xiao.server.factory;

import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用于存储内部对话的session
 *
 * @author lao xiao
 * @date 2022年10月29日 17:20
 */
@Component
public class SessionClientInnerImpl implements Session {

    /**
     * 存储当前的会话信息
     * key: 对方的服务端的ip+port
     *
     */
    private final Map<String, Channel> usernameChannelMap = new ConcurrentHashMap<>();

    @Override
    public void bind(Channel channel, String user) {
        usernameChannelMap.put(user, channel);
    }

    @Override
    public Channel getChannel(String userName) {
        return usernameChannelMap.get(userName);
    }
}
