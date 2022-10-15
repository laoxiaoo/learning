package com.xiao.converter.strategy;

import com.xiao.converter.Parameter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author lao xiao
 * @ClassName AbstractConverterStrategy.java
 * @Description
 * @createTime 2021年04月07日 10:27:00
 */
public abstract class AbstractConverterStrategy<T extends Parameter> implements ConverterStrategy {

    @Override
    public String getKey() {
        Type type = getClass().getGenericSuperclass();
        Type trueType = ((ParameterizedType) type).getActualTypeArguments()[0];
        return ((Class<T>) trueType).toString();
    }
}
