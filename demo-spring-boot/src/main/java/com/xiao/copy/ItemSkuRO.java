package com.xiao.copy;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author lao xiao
 * @date 2023-12-14 19:39
 */
@Getter
@Setter
public class ItemSkuRO implements Serializable {

    /**
     * sku编码
     */
    private String barcode;

    /**
     * 关联t_item表的item_id
     */
    private Integer itemId;

}
