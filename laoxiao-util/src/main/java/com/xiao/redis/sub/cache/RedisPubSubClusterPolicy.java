package com.xiao.redis.sub.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * @author xiao jie
 * @date 2023-10-02 10:00
 */
@Slf4j
public class RedisPubSubClusterPolicy extends JedisPubSub implements ClusterPolicy , ApplicationContextAware {

    private static final String  CHANNEL = "GREAT:USER:SUB:CHANNEL";

    private ApplicationContext applicationContext;

    @Autowired
    private JedisPool jedisPool;

    @Override
    public void onMessage(String channel, String message) {
        //发送spring事件
        if(CHANNEL.equals(channel)) {
            log.info("接收到相关Redis的事件:{}", message);
            RedisSubApplicationEvent redisSubApplicationEvent = new RedisSubApplicationEvent(applicationContext);
            redisSubApplicationEvent.setCommand(message);
            applicationContext.publishEvent(redisSubApplicationEvent);
        }
    }

    @Override
    public void publish(Command cmd) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.publish(CHANNEL, String.valueOf(cmd.getOperator()));
        }
    }

    @EventListener(classes = ContextRefreshedEvent.class)
    public void springInitEvent(ContextRefreshedEvent event) {
        try {
            connect();
        } catch (Exception e) {
            log.error("发布redis订阅数据异常，可能造成缓存失效， 请重视，", e);
        }
    }

    @Override
    public void connect() {
        this.publish(Command.join());
        Thread subscribeThread = new Thread(()-> {
            //当 Redis 重启会导致订阅线程断开连接，需要进行重连
            while(!jedisPool.isClosed()) {
                try (Jedis jedis = jedisPool.getResource()){
                    jedis.subscribe(this, CHANNEL);
                    log.info("Disconnect to redis channel: {}", CHANNEL);
                    break;
                } catch (JedisConnectionException e) {
                    log.error("Failed connect to redis, reconnect it.", e);
                    if(!jedisPool.isClosed())
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ie){
                        break;
                    }
                }
            }
        }, "RedisSubscribeThread");
        subscribeThread.setDaemon(true);
        subscribeThread.start();
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
