package com.xiao.copy;

import cn.hutool.core.util.ClassUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author xiao jie
 * @date 2023-09-09 09:44
 */
@Slf4j
public class BeanCopyUtil {

    /**
     * 对source进行拷贝，如果遇到属性相同，则进行浅拷贝
     *
     * @param source
     * @param target
     * @param <K>
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <K, T> T copy(K source, Class<T> target) {
        BeanCopier copier = BeanCopier.create(source.getClass(), target, false);
        try {
            T res = target.newInstance();
            copier.copy(source, res, null);
            return res;
        } catch (Exception e) {
            log.error("Bean Copy error:", e);
        }
        return null;
    }

    /**
     * 对source进行拷贝，如果遇到属性相同，则进行深拷贝
     *
     * @param source
     * @param target
     * @param <K>
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <K, T> T copyDeep(K source, Class<T> target) {
        BeanCopier copier = BeanCopier.create(source.getClass(), target, true);
        try {
            T res = target.newInstance();
            copier.copy(source, res, new DefaultConverter());
            return res;
        } catch (Exception e) {
            log.error("Bean Copy error:", e);
        }
        return null;
    }


    /**
     * 对集合进行拷贝
     *
     * @param srcList
     * @param clz
     * @param <T>
     * @param <V>
     * @return
     */
    public static <T, V> Collection<T> copyList(Collection<V> srcList, Class<T> clz) {
        return copyList(srcList, var -> copy(var, clz));
    }

    /**
     * 对集合进行拷贝，如果遇到属性相同，则进行深拷贝
     *
     * @param srcList
     * @param clz
     * @param <T>
     * @param <V>
     * @return
     */
    public static <T, V> Collection<T> copyDeepList(Collection<V> srcList, Class<T> clz) {
        return copyList(srcList, var -> copyDeep(var, clz));
    }


    private static <T, V> Collection<T> copyList(Collection<V> srcList, Function<? super V, ? extends T> mapper) {
        if (srcList != null && srcList.size() > 0) {
            return srcList.stream().map(mapper::apply).collect(Collectors.toList());
        }
        return null;
    }


    static class DefaultConverter implements Converter {

        static Set<ConverterTypes> SETS = new HashSet<>();

        static {
            Set<Class<?>> classes = ClassUtil.scanPackageBySuper(DefaultConverter.class.getPackage().getName(), ConverterTypes.class);
            classes.forEach(var -> {
                try {
                    Object o = var.newInstance();
                    SETS.add((ConverterTypes)o);
                } catch (Exception e) {
                    log.error("newInstance error :" + e);
                }
            });
        }

        @Override
        public Object convert(Object o, Class aClass, Object o1) {
            for(ConverterTypes converterTypes : SETS) {
                if(converterTypes.isTypes(o, aClass, o1)) {
                    return converterTypes.convert(o, aClass, o1);
                }
            }
            return o;
        }
    }

    protected static interface ConverterTypes {

        Boolean isTypes(Object o, Class aClass, Object o1);

        Object convert(Object o, Class aClass, Object o1);
    }

    static class CollectionConverterTypes implements ConverterTypes {

        @Override
        public Boolean isTypes(Object o, Class aClass, Object o1) {
            return o instanceof Collection;
        }

        @Override
        public Object convert(Object o, Class aClass, Object o1) {
            return ((Collection)o).stream().map(var -> copy(var, var.getClass())).collect(Collectors.toList());
        }
    }

}
