package com.xiao.util.cache;

import cn.hutool.core.lang.func.Func;
import cn.hutool.extra.spring.SpringUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;

/**
 * @author lao xiao
 * @date 2022年11月12日 15:21
 */
public class CacheUtil {
    /**
     * 为空的时候存储的数据
     */
    private static final String NULL_OBJECT = "$$";

    /**
     * 从 function中获取数据
     * @param list
     * @param function
     * @return
     * @param <T>
     * @param <R>
     */
    public static <T, R> List<R> getObjectList(List<T> list, Function<T, R> function) {
        if(list==null || list.size()==0) {
            return Collections.emptyList();
        }
        Iterator<T> iterator = list.iterator();
        List<R> recList = new ArrayList(list.size());
        while (iterator.hasNext()) {
            R object = function.apply(iterator.next());
            if(Objects.nonNull(object)) {
                iterator.remove();
                recList.add(object);
            }
        }
        return recList;
    }


    /**
     * 如果返回$$证明当前数据库不存在数据
     * @param list 传入的参数，一般是id
     * @param function 一般从缓存查询数据,返回一个string类型的数据，用于反序列化
     * @param deserialization 反序列化的操作
     * @return
     * @param <T>
     * @param <R>
     */
    public static <T, R> List<R> getObjectList(List<T> list, Function<T, String> function, Function<String, R> deserialization) {
        if(list==null || list.size()==0) {
            return Collections.emptyList();
        }
        Iterator<T> iterator = list.iterator();
        List<R> recList = new ArrayList(list.size());
        while (iterator.hasNext()) {
            String str = function.apply(iterator.next());
            if(Objects.nonNull(str) && !NULL_OBJECT.equals(str)) {
                R object = deserialization.apply(str);
                iterator.remove();
                recList.add(object);
            } else if (Objects.nonNull(str) && NULL_OBJECT.equals(str)) {
                iterator.remove();
            }
        }
        return recList;
    }

    /**
     * 从缓存获取数据，如果缓存不存在，则db查询
     * @param ids
     * @param keyFunction
     * @param deserialization
     * @param dbFunction
     * @return
     * @param <T>
     * @param <R>
     */
    public static <T, R> List<R> getObjectList(List<T> ids, Function<T, String> keyFunction, Function<String, R> deserialization, Function<List<T>, List<R>> dbFunction) {
        if(ids==null || ids.size()==0) {
            return Collections.emptyList();
        }
        List<R> recList = new ArrayList(ids.size());
        //db存在的id
        List<T> dbIds = new ArrayList<>(ids.size());
        ICache cache = null; //SpringUtil.getBean(ICache.class);
        for(T id : ids) {
            String str = cache.getString(keyFunction.apply(id));
            if(Objects.nonNull(str) && !NULL_OBJECT.equals(str)) {
                R object = deserialization.apply(str);
                recList.add(object);
            } else if (Objects.isNull(str) || !NULL_OBJECT.equals(str)) {
                dbIds.add(id);
            }
        }
        if(dbIds.size() > 0) {
            List<R> list = dbFunction.apply(dbIds);
            if(list!=null && list.size()>0) {
                list.forEach(var -> cache.setObject(var));
                recList.addAll(list);
            }
        }
        return recList;
    }




}
