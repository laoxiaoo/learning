package com.xiao.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author xiao jie
 * @create 2021年10月20日 16:10:00
 */
public class JacksonUtil {

    private static ObjectMapper mapper ;

    static {
        mapper = new ObjectMapper();
        //序列化结果格式化
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        //空对象不抛出异常
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        //Date、Calendar等序列化为时间格式的字符串
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        //反序列化时，遇到未知属性不要抛出异常
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //反序列化时，空字符串对于的实例属性为null
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        //允许字段名没有引号
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        //允许单引号
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    }

    /**
     * 将Object对象转换为字符串
     * @param t
     * @param <T>
     * @return
     */
    public String toJsonString(Object value) {
        String s;
        try {
            s = mapper.writeValueAsString(value);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        return s;
    }

}
