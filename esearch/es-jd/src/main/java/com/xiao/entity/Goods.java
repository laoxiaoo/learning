package com.xiao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program: learning
 * @description: TODO
 * @author: lonely xiao
 * @create: 2020-10-08 16:12
 */
@ToString
@Getter
@Setter
@AllArgsConstructor
public class Goods {

    private String price;

    private String img;

    private String title;
}