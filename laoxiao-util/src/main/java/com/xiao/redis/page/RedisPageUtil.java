package com.xiao.redis.page;

import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * redis 分页操作的一些工具类
 *
 * @author lao xiao
 * @date 2024-03-02 14:48
 */
public class RedisPageUtil {

    /**
     * 根据lrange命令进行分页
     * @param pageIndex
     * @param pageSize
     * @param biFunction
     * @return
     * @param <R>
     */
    public static <R> List<R> pageLRange(Integer pageIndex, Integer pageSize, BiFunction<Integer, Integer, List<R>> biFunction) {
        if(Objects.isNull(pageIndex)) {
            pageIndex = 1;
        }
        if(Objects.isNull(pageSize)) {
            pageSize = 20;
        }
        int index =(pageIndex-1)*pageSize;
        int stop = index+pageSize-1;
        return biFunction.apply(index, stop);
    }

}
