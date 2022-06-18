package com.xiao.pojo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * mapper 文件的对应的属性类
 * @author lao xiao
 * @create 2022年05月22日 21:57:00
 */
@Getter
@Setter
@ToString
public class MapperStatement {

    private String id;

    private String resultType;

    private String paramType;
    /**
     * sql语句
     */
    private String sql;

}
