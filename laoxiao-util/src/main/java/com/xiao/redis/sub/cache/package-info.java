/**
 *
 *
 * jedis 对redis 的发布与订阅进行缓存操作
 * 基本流程，当用户对db进行修改新增操作后， 发布消息，通知其他服务(分布式环境下)收到消息，自己去更新自己的本地缓存
 *
 * 仿造j2cache
 *
 *  使用方式:
 *  1. 在容器注入JedisPool的bean，同时注入RedisPubSubClusterPolicy bean
 *  2. spring监听RedisSubApplicationEvent事件类型，在command判断事件类型对缓存操作
 *  3. 当 db数据发生更改， 调用RedisPubSubClusterPolicy#publish(com.xiao.redis.sub.cache.Command)
 *  发送事件，监听了RedisSubApplicationEvent事件的方法对缓存进行调整
 *
 *
 *
 */
package com.xiao.redis.sub.cache;