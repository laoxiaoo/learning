package com.xiao.algorithm.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * <ul>
 *     从右边开始比较相邻两个数的大小，再根据结果交换位置
 *     然后开始向左偏移交换位置
 *
 * </ul>
 * <ul>
 *     这样，最左边的一个数一定是最小（最大一个数）
 * </ul>
 *
 * @author xiao ji hao
 * @create 2021年06月29日 10:45:00
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] sort = sort(new int[]{4, 5, 6, 2, 7, 1, 10, 8});
        System.out.println(Arrays.toString(sort));
    }

    public static int[] sort(int[] nums) {
        for(int i=nums.length-1; i>=0; i--) {
            for(int j=i; j>0; j--) {
                //相邻的两位开始比
                if(nums[j]<nums[j-1]){
                    int tmp = nums[j];
                    nums[j] = nums[j-1];
                    nums[j-1] = tmp;
                }
            }
        }
        return nums;
    }

}
