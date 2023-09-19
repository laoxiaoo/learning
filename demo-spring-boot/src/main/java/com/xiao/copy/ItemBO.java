package com.xiao.copy;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author xiao jie
 * @date 2023-09-09 10:55
 */
@Getter
@Setter
public class ItemBO implements Serializable {
    /** 商品名称 */
    private String itemName;
    /** 商品短标题 */
    private String itemShortName;

    private List<ItemSkuBO> itemSkus;



}
