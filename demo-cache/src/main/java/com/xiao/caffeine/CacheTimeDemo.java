package com.xiao.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

/**
 * 缓存固定数量的key，每个key过期时间指定
 *
 *
 *
 *
 * @author lao xiao
 * @date 2024-06-12 16:05
 */
public class CacheTimeDemo {

    static Cache<String, String> cache = Caffeine.newBuilder()
            .expireAfterWrite(10, TimeUnit.MINUTES)  //10分钟过期
            .maximumSize(10_000) //最大存储数量
            .build();

    public static void main(String[] args) {
        String key = "";
        // 查找一个缓存元素， 没有查找到的时候返回null
        String graph = cache.getIfPresent(key);
        // 查找缓存，如果缓存不存在则生成缓存元素,  如果无法生成则返回null
        graph = cache.get(key, k -> createExpensiveGraph(key));
        // 添加或者更新一个缓存元素
        cache.put(key, graph);
        // 移除一个缓存元素
        cache.invalidate(key);
    }

    private static String createExpensiveGraph(String key) {
        return "";
    }

}
