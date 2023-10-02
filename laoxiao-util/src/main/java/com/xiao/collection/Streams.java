package com.xiao.collection;


import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Stream流操作工具类
 * @author lx
 * @create 2021年09月18日 11:45:00
 */
public class Streams {

    /**
     * 按照某种规则对集合进行分组
     * @param data
     * @param mapper  获取聚合的key
     * @param <T>
     * @param <E>
     * @return
     */
    public static <T, E> Map<E, List<T>> groupBy(Collection<T> data, Function<? super T, ? extends E> mapper) {
        return data.stream().collect(Collectors.groupingBy(mapper));
    }


    /**
     * 将集合转为map
     * @param data
     * @param keyMapper
     * @param valueMapper
     * @param mergeFunction
     * @param <T>
     * @param <K>
     * @param <E>
     * @param <U>
     * @return
     */
    public static <T, K, E, U> Map<K, U> toMap(Collection<T> data, Function<? super T, ? extends K> keyMapper,
                                         Function<? super T, ? extends U> valueMapper,
                                         BinaryOperator<U> mergeFunction) {
        return data.stream().collect(Collectors.toMap(keyMapper, valueMapper, mergeFunction));
    }

    /**
     * 将集合转为map，抽出key，value为集合的单个实体
     * @param data
     * @param keyMapper  map的key
     * @param <T>
     * @param <K>
     * @param <E>
     * @return
     */
    public static <T, K, E> Map<K, T> toMap(Collection<T> data, Function<? super T, ? extends K> keyMapper) {
        return toMap(data, keyMapper, var -> var);
    }



    /**
     * 将集合转为map
     * @param data 数据源
     * @param keyMapper map的键
     * @param valueMapper map 的value
     * @param <T>
     * @param <K>
     * @param <E>
     * @param <U>
     * @return
     */
    public static <T, K, E, U> Map<K, U> toMap(Collection<T> data, Function<? super T, ? extends K> keyMapper,
                                               Function<? super T, ? extends U> valueMapper) {
        if(data == null) {
            return new HashMap<>();
        }
        return toMap(data, keyMapper, valueMapper, (tmp1, tmp2) -> tmp1);
    }




    /**
     * 将集合转为map， value值作为聚合
     * @param data
     * @param keyMapper
     * @param valueMapper
     * @param <T>
     * @param <K>
     * @param <E>
     * @param <U>
     * @return
     */
    public static <T, K, E, U> Map<K, List<U>> toMapGroup(Collection<T> data, Function<? super T, ? extends K> keyMapper,
                                               Function<? super T, ? extends List<U>> valueMapper) {
        return toMap(data, keyMapper, valueMapper, (tmp1, tmp2) -> {
            tmp1.addAll(tmp2);
            return tmp1;
        });
    }

    /**
     * 将集合转换成set
     * @param data
     * @param function
     * @return
     * @param <T>
     * @param <K>
     */
    public static <T, K> Set<K> toSet(Collection<T> data, Function<T, K> function) {
        if(data == null) {
            return Collections.EMPTY_SET;
        }
        return data.stream().map(function::apply).collect(Collectors.toSet());
    }

    /**
     * 筛选某个字段集合
     * @param data
     * @param screen
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> List<R> screenField(Collection<T> data, Function<? super T, ? extends R> screen) {
        return screenField(data, Objects::nonNull, screen);
    }

    /**
     * 刷选某个字段集合
     * @param data
     * @param predicate
     * @param screen
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> List<R> screenField(Collection<T> data, Predicate<? super R> predicate, Function<? super T, ? extends R> screen) {
        if(data==null) {
            return Collections.emptyList();
        }
        return data.stream().map(screen).filter(predicate::test).collect(Collectors.toList());
    }

}
