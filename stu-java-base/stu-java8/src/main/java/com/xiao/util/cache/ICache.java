package com.xiao.util.cache;

/**
 * @author lao xiao
 * @date 2022年11月14日 9:21
 */
public interface ICache {

    <T> String getString(T param);

    <T> void setObject(T object);

}
