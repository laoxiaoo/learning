package com.xiao.demo;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author 肖杰
 * @ClassName IdJsonSerializer.java
 * @Description
 * @createTime 2021年03月29日 15:18:00
 */
public class IdJsonSerializer extends JsonSerializer<Long> {
    @Override
    public void serialize(Long value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        //对该字段进行
        gen.writeNumber(value+100);
        //新增一个字段
        gen.writeNumberField("id_tmp", value);
    }
}
