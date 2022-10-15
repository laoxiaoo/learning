package com.xiao.demo;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.Deserializers;

import java.io.IOException;

/**
 * @author lao xiao
 * @ClassName IdDeJsonSeriablizer.java
 * @Description
 * @createTime 2021年03月29日 15:48:00
 */
public class IdJsonDeserializer extends JsonDeserializer<Long> {
    @Override
    public Long deserialize(JsonParser p, DeserializationContext context) throws IOException, JsonProcessingException {
        return 222L;
    }
}
