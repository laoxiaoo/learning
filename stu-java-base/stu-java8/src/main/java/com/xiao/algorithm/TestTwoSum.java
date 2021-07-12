package com.xiao.algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 *
 * 求两数之和
 * <ul>
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * @author xiao ji hao
 * @create 2021年06月28日 12:01:00
 */
@Slf4j
public class TestTwoSum {
    public static int[] method(int[] nums, int target) {
        int[] ret = new int[2];
        for(int i=0; i<nums.length; i++) {
            for(int j=i+1; j<nums.length; j++) {
                int tmp = nums[i] + nums[j];
                if(tmp == target) {
                    ret[0] = i;
                    ret[1] = j;
                    return ret;
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] ints = method(new int[]{2, 7, 11, 15}, 9);
        System.out.println(Arrays.toString(ints));
    }
}
