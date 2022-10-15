package com.xiao.pojo;


import lombok.*;

import java.io.Serializable;

/**
 * @author lao xiao
 * @version 1.2.8
 * @ClassName Student.java
 * @Description
 * @createTime 2020年11月10日 08:53:00
 */
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {

    private Long id;

    private String name;

    private String phoneNumber;
}
