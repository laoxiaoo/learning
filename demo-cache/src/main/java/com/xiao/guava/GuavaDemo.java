package com.xiao.guava;

import com.google.common.cache.*;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author lao xiao
 * @create 2022年05月11日 20:50:00
 */
@Slf4j
public class GuavaDemo {

    private static LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder()
            //最大的条目数
            .maximumSize(20)
            //缓存过期时间
            .expireAfterAccess(20, TimeUnit.SECONDS)
            //缓存删除监听
            .removalListener(new RemovalListener<String, String>() {
                @Override
                public void onRemoval(RemovalNotification<String, String> removalNotification) {
                    log.info("缓存被删除：{} ", removalNotification);
                }
            })
            //缓存key不存在加载方法
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String s) throws Exception {
                    log.debug("缓存加载：{}", s);
                    return "cache_"+s;
                }
            });


    /**
     * 通过key 获取缓存数据，如果key不存在，调用 load 方法 加载缓存
      * @param key
     * @return
     * @throws Exception
     */
    public static String get(String key) throws Exception {
        return loadingCache.get(key);
    }

    /**
     * 删除当前key的缓存
     * 此时会触发removalListener监听器
     * @param key
     */
    public static void remove(String key) {
        loadingCache.invalidate(key);
    }

    /**
     * 删除所有缓存
     * 此时会触发removalListener监听器
     */
    public static void removeAll() {
        loadingCache.invalidateAll();
    }

    /**
     *
     * 保存缓村数据， 如果缓存中有该数据，则会移除该key的缓存，
     * 此时会触发removalListener监听器
     */
    public static void put() {
        loadingCache.put("laoxiao", "aaaa");
    }


    public static void main(String[] args) throws Exception {
        for(int i=0; i<=100; i++) {
            new Thread(() -> {
                try {
                    System.out.println(get("laoxiao"));;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
        TimeUnit.SECONDS.sleep(100000);
    }
}
