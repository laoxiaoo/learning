package com.xiao.converter;

/**
 * @author 肖杰
 * @ClassName Converter.java
 * @Description
 * @createTime 2021年04月07日 09:53:00
 */
public interface Converter {

    /**
     * pdf 转换的方法， 可以是图片转，也可以是
     * @param parameter
     * @param <T>
     * @return
     */
    <T extends Response> T convert(Parameter parameter);

}
