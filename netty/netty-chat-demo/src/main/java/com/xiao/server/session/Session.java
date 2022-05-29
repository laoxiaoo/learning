package com.xiao.server.session;


import io.netty.channel.Channel;

/**
 * 会话管理
 * @author lao xiao
 * @create 2022年05月07日 21:38:00
 */
public interface Session {

    void bind(Channel channel, String username);

    /**
     * 通过用户名获取channel
     * @param userName
     * @return
     */
    Channel getChannel(String userName);

    /**
     * 通过当前channel获取当前用户
     * @param channel
     * @return
     */
    String getUser(Channel channel);

}
