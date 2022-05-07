package com.xiao.test;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Optional;

/**
 * @author xiao jie
 * @create 2022年02月16日 11:05:00
 */
public class Test1 {

    public static void main(String[] args) {
        ActivityTimesItemNew itemBo = new ActivityTimesItemNew();
        itemBo.setSellPointTxt("111");
        Optional.ofNullable(itemBo)
                .map(ActivityTimesItemNew::getSellPointType)
                .filter(var -> 1 == var)
                .ifPresent(var -> {
                    itemBo.setSellPointTxt(null);
                });
        System.out.println(itemBo);
    }

    @Getter
    @Setter
    @ToString
    static class ActivityTimesItemNew {
        private Integer sellPointType;
        private String sellPointTxt;
    }
}
