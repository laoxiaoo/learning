package com.xiao.util.chat.factory;

import io.netty.channel.Channel;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author lao xiao
 * @date 2022年10月19日 11:22
 */
public interface Session {

    void bind(Channel channel, String user);

    Channel getChannel(String userName);


    default Channel getChannel(String userName, BiFunction<String, Integer, Channel> biFunction){
        return null;
    }

    /**
     * 删除channel
     */
    void removeChannel(Channel channel);

}
