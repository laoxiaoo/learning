package com.xiao.copy;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author xiao jie
 * @date 2023-09-09 10:56
 */
@Getter
@Setter
public class ItemSkuBO implements Serializable {

    /**
     * sku编码
     */
    private String barcode;

    /**
     * 关联t_item表的item_id
     */
    private Integer itemId;

}
