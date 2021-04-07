package com.xiao.demo;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.sun.org.apache.bcel.internal.generic.FADD;
import com.xiao.entity.Person;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;

/**
 * @author 肖杰
 * @ClassName JsonFactoryDemo.java
 * @Description
 * @createTime 2021年03月26日 16:04:00
 */
@Slf4j
public class JsonFactoryDemo {

    public static void main(String[] args) throws Exception {
        serialize();
    }

    static JsonFactory jsonFactory = new JsonFactory();
    /**
     * 反序列化（json -> object）
     */
    private static void deserializeJSONStr() throws Exception {
        String json = "{ \"name\": \"laoxiao\", \"id\": 1 }";
        JsonParser parser = jsonFactory.createParser(json);
        if(parser.nextToken() != JsonToken.START_OBJECT) {
            parser.close();
            System.out.println("==> json 起始符号不是 {");
            return;
        }
        Person person = new Person();
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = parser.getCurrentName();
            log.info("==>正在解析字段 [{}]", fieldName);
            parser.nextToken();
            switch (fieldName) {
                case "id": person.setId(parser.getLongValue()); break;
                case "name": person.setName(parser.getText()); break;
            }
        }
        System.out.println(person);
        parser.close();
    }

    private static void serialize() throws Exception {
        Person person = new Person(1L, "laoxiao", "laoxia", null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        JsonGenerator factoryGenerator = jsonFactory.createGenerator(byteArrayOutputStream);
        factoryGenerator.useDefaultPrettyPrinter();
        factoryGenerator.writeStartObject();
        factoryGenerator.writeNumberField("id", person.getId());
        factoryGenerator.writeStringField("name", person.getName());
        factoryGenerator.writeEndObject();
        factoryGenerator.close();

        System.out.println("<==json 序列化："+ byteArrayOutputStream.toString());
    }
}
