package com.xiao.server.factory;

import cn.hutool.core.util.StrUtil;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

/**
 * @author lao xiao
 * @date 2022年10月19日 11:22
 */
@Component
@Primary
public class SessionRedisImpl implements Session {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("chat.port")
    private String port;

    @Autowired
    @Qualifier(value = "sessionMemoryImpl")
    private Session session;

    private static String USER_ID="user_id:{}";

    @Override
    public void bind(Channel channel, String user) {
        //存储内存中
        session.bind(channel, user);
        //key：user   value:连接客户端的channel的ip（本地ip）
        try {
            stringRedisTemplate.opsForValue().set(StrUtil.format(USER_ID, user), InetAddress.getLocalHost().getHostAddress()+":"+port);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Channel getChannel(String user) {
        String ipPort = stringRedisTemplate.opsForValue().get(StrUtil.format(USER_ID, user));
        String[] ipStr = Optional.ofNullable(ipPort).map(var -> var.split(":")).orElseGet(() -> null);

        return null;
    }
}
