package com.xiao.converter.strategy;

import com.xiao.converter.Parameter;
import com.xiao.converter.Response;

/**
 * @author lao xiao
 * @ClassName ConverterStrategy.java
 * @Description
 * @createTime 2021年04月07日 10:14:00
 */
public interface ConverterStrategy<T extends Parameter> {

    /**
     * 策略模式执行的方法匹配值
     * @return
     */
    String getKey();

    /**
     * 真正做事的方法
     * @param parameter
     * @return
     */
    Response dealEvent(T parameter);

}
