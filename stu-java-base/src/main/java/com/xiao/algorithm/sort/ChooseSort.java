package com.xiao.algorithm.sort;

import java.util.Arrays;

/**
 * 选择排序
 * <ul>
 *     选择最小的一个数，与最左边的一个数交换，然后再在n-1中的一个数中选择最小的一个数
 * </ul>
 * @author xiao ji hao
 * @create 2021年06月29日 11:13:00
 */
public class ChooseSort {


    public static void main(String[] args) {
        int[] sort = sort(new int[]{4, 5, 6, 2, 7, 1, 10, 8});
        System.out.println(Arrays.toString(sort));
    }

    public static int[] sort(int[] nums) {
        for(int i=0; i<nums.length; i++) {
            int tmp = nums[i];
            int index = i;
            for(int j=i+1; j<nums.length; j++) {
                if(nums[j]<tmp) {
                    tmp = nums[j];
                    index = j;
                }
            }
            nums[index] = nums[i];
            nums[i] = tmp;
        }
        return nums;
    }

}
