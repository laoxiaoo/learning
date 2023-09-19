package com.xiao.copy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author xiao jie
 * @date 2023-09-09 10:57
 */
public class BeanCopyUtilTest {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.xiao.copy.BeanCopyUtil");
        ItemSkuBO itemSkuBO = new ItemSkuBO();
        itemSkuBO.setItemId(1);
        itemSkuBO.setBarcode("11111");
        ItemBO itemBO = new ItemBO();
        itemBO.setItemName("test");
        itemBO.setItemShortName("test");
        List<ItemSkuBO> list = new ArrayList<>();
        list.add(itemSkuBO);
        itemBO.setItemSkus(list);
        System.out.println(itemBO.hashCode() + "==" + itemBO.getItemSkus().hashCode());
        itemBO.getItemSkus().forEach(var -> System.out.println(var.hashCode()));

        //======================
        ItemBO itemBOCopy4 = BeanCopyUtilProxy.copy(itemBO, ItemBO.class);
        System.out.println(itemBOCopy4.hashCode() + "==" + itemBOCopy4.getItemSkus().hashCode());
        itemBOCopy4.getItemSkus().forEach(var -> System.out.println(var.hashCode()));

        //==================
        ItemBO itemBOCopy = BeanCopyUtilProxy.copyDeep(itemBO, ItemBO.class);
        System.out.println(itemBOCopy.hashCode() + "==" + itemBOCopy.getItemSkus().hashCode());
        itemBOCopy.getItemSkus().forEach(var -> System.out.println(var.hashCode()));
        //=====================
        ItemBO itemBOCopy2 = BeanCopyUtilProxy.copy(itemBO, ItemBO.class);
        System.out.println(itemBOCopy2.hashCode() + "==" + itemBOCopy2.getItemSkus().hashCode());
        itemBOCopy2.getItemSkus().forEach(var -> System.out.println(var.hashCode()));
        //========================
        ItemBO itemBOCopy3 = BeanCopyUtilProxy.copyDeep(itemBO, ItemBO.class);
        System.out.println(itemBOCopy3.hashCode() + "==" + itemBOCopy3.getItemSkus().hashCode());
        itemBOCopy3.getItemSkus().forEach(var -> System.out.println(var.hashCode()));
    }

}
