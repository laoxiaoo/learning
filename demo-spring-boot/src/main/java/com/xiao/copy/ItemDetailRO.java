package com.xiao.copy;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author lao xiao
 * @date 2023-12-14 19:38
 */
@Getter
@Setter
public class ItemDetailRO implements Serializable {

    /** 商品名称 */
    private String itemName;
    /** 商品短标题 */
    private String itemShortName;

    private List<ItemSkuRO> itemSkus;

}
