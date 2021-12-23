package com.xiao.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.xiao.demo.IdJsonDeserializer;
import com.xiao.demo.IdJsonSerializer;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 肖杰
 * @version
 * @ClassName Person.java
 * @Description
 * @createTime 2021年03月26日 15:39:00
 */
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @JsonSerialize(using = IdJsonSerializer.class)
    @JsonDeserialize(using = IdJsonDeserializer.class)
    private Long id;

    private String name;

    @JsonProperty(value = "file_name")
    private String fileName;

    @JsonAnySetter
    private Map<String, String> map = new HashMap<>();

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime time;



    @JsonAnyGetter
    private Map<String, String> getMap() {
        return map;
    }

    @JsonCreator
    public Person(@JsonProperty("id_field") Long id) {

    }

}
