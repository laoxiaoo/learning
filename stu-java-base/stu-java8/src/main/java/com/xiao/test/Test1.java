package com.xiao.test;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author xiao jie
 * @create 2022年02月16日 11:05:00
 */
public class Test1 {

    public static void main(String[] args) {
        /*ActivityTimesItemNew itemBo = new ActivityTimesItemNew();
        itemBo.setSellPointTxt("111");
        Optional.ofNullable(itemBo)
                .map(ActivityTimesItemNew::getSellPointType)
                .filter(var -> 1 == var)
                .ifPresent(var -> {
                    itemBo.setSellPointTxt(null);
                });
        System.out.println(itemBo);*/

        CollUtil.newArrayList(1,2,3,4).stream().map(var -> {
            System.out.println(var);
            return var;
        });
        List<String> split = StrUtil.split("11111,222", ",");
        System.out.println(split.stream().filter(StrUtil::isNotBlank).map(Integer::valueOf).collect(Collectors.toList()));
    }

    @Getter
    @Setter
    @ToString
    static class ActivityTimesItemNew {
        private Integer sellPointType;
        private String sellPointTxt;
    }
}
