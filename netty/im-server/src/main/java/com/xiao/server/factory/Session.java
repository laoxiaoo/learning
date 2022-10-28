package com.xiao.server.factory;

import io.netty.channel.Channel;

/**
 * @author lao xiao
 * @date 2022年10月19日 11:22
 */
public interface Session {

    void bind(Channel channel, String user);

    Channel getChannel(String userName);

}
