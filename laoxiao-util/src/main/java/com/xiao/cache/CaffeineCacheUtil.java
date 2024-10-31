package com.xiao.cache;

import com.github.benmanes.caffeine.cache.Cache;

import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Function;

/**
 * @author lao xiao
 * @date 2024-09-20 16:06
 */
public class CaffeineCacheUtil<T,R> {

    private static final ReentrantReadWriteLock rw =  new ReentrantReadWriteLock();
    private static final ReentrantReadWriteLock.ReadLock r = rw.readLock();
    private static final ReentrantReadWriteLock.WriteLock w = rw.writeLock();

    Cache<T, R> cache;

    public CaffeineCacheUtil(Cache<T,R> cache) {
        this.cache = cache;
    }


    public Map<T, R> getAlls(List<T> ts, Function<List<T>,Map<T, R>> dbFunction) {
        if (Objects.isNull(ts) || ts.isEmpty()) {
            return Collections.emptyMap();
        }
        r.lock();
        List<T> missingIds = new ArrayList<>(ts.size());
        Map<T,R> res = new HashMap<>(ts.size());
        try {
            for (T t : ts) {
                R obj = cache.getIfPresent(t);
                if (Objects.isNull(obj)) {
                    missingIds.add(t);
                }
                res.put(t, obj);
            }
        } finally {
            r.unlock();
        }
        if (!missingIds.isEmpty()) {
            w.lock();
            try {
                Map<T, R> rs = dbFunction.apply(missingIds);
                if (Objects.nonNull(rs) && !rs.isEmpty()) {
                    rs.forEach((k,v) -> {
                        res.put(k, v);
                        cache.put(k,v);
                    });
                }
            }finally {
                w.unlock();
            }
        }
        return res;
    }


    public R get(T t, Function<T, R> function){
        return cache.get(t, function);
    }

}
