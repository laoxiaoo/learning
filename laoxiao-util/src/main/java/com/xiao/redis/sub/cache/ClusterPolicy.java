package com.xiao.redis.sub.cache;


/**
 *  仿自J2Cache
 * 利用redis的订阅， 发送 RedisSubApplicationEvent 事件
 * 在监听RedisSubApplicationEvent事件时，对系统做一些操作
 *
 * 如果需要 触发 事件发布，则调用 com.xiao.redis.sub.cache.ClusterPolicy#publish(com.xiao.redis.sub.cache.Command)
 *
 *
 * 调用示例
 *    @Bean
 *     public ClusterPolicy clusterPolicy() {
 *         return new RedisPubSubClusterPolicy();
 *     }
 *
 *     在某个地方，
 *      ClusterPolicy.publish
 *      在监听地方
 *      @EventListener(classes = {
 *             RedisSubApplicationEvent.class
 *     })
 *
 *
 * @author xiao jie
 * @date 2023-10-02 10:00
 */
public interface ClusterPolicy {


    void publish(Command cmd);

    void connect();

}
