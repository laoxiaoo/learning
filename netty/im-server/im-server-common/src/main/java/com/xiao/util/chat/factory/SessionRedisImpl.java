package com.xiao.util.chat.factory;

import cn.hutool.core.util.StrUtil;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 * @author lao xiao
 * @date 2022年10月19日 11:22
 */
@Slf4j
public class SessionRedisImpl implements Session {

    //存储客户端的channel
    private static Map<String, Channel> map = new ConcurrentHashMap<>();
    private static Map<Channel, String> mapTmp = new ConcurrentHashMap<>();

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${chat.inner.port}")  //模拟另一台服务器将来用注册的方式获取
    private String port;

    private Session session;

    public void setSession(Session session) {
        this.session = session;
    }

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
        throw new RuntimeException("不支持的方法");
    }


    @Override
    public Channel getChannel(String userName, BiFunction<String, Integer, Channel> biFunction) {
        String ipPort = stringRedisTemplate.opsForValue().get(StrUtil.format(USER_ID, userName));
        String[] servers = Optional.ofNullable(ipPort).map(var -> var.split(":")).orElseGet(() -> null);
        String ip = servers[0];
        Integer port = Integer.valueOf(servers[1]);
        Channel channel = map.computeIfAbsent(ipPort, var -> {
            Channel ch = biFunction.apply(ip, port);
            mapTmp.put(ch, userName);
            return ch;
        });
        return channel;
    }

    @Override
    public void removeChannel(Channel channel) {
        mapTmp.computeIfPresent(channel , (k,v) -> {
            String ipPort = stringRedisTemplate.opsForValue().get(StrUtil.format(USER_ID, v));
            map.remove(ipPort);
            stringRedisTemplate.delete(StrUtil.format(USER_ID, v));
            return null;
        });
        log.info("删除channel：{}", channel);
    }
}
