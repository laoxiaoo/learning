package com.xiao.demo;

import cn.hutool.core.collection.CollUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.xiao.entity.Person;
import com.xiao.util.JacksonUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lao xiao
 * @ClassName ObjectMaperDemo.java
 * @Description
 * @createTime 2021年03月26日 17:38:00
 */
public class ObjectMapperDemo {

    public static void main(String[] args) throws Exception {
        deserializeJSONStr();
    }
    static ObjectMapper mapper = new ObjectMapper();

    static {
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

    private static void serializeJSONStr() throws Exception {
        Person person = new Person(1L, "laoxiao", "test", null);
        Map<String, String> map = new HashMap<>(1);
        map.put("aaaa", "aaa");
        person.setMap(map);
        //String json = mapper.writeValueAsString(person);
        String json = JacksonUtil.toJsonString(person);
        System.out.println("==> 解析的json"+json);
    }

    private static void deserializeJSONStr() throws Exception {
        String json = "{ \"name\": \"laoxiao\", \"id\": 1, \"file_name\": \"测试中\", \"tests\": \"testsss\" }";
       // Person person = mapper.readValue(json, Person.class);
        Person person = JacksonUtil.toBean(json, Person.class);
        System.out.println("<==序列化的对象："+person);
    }


}
