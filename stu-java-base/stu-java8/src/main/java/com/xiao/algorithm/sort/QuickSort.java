package com.xiao.algorithm.sort;

import java.util.Arrays;

/**
 * 快排序
 * <ul/>
 *
 * 4, 5, 6, 2, 7, 1, 10, 8
 * <br/>
 * 选择一个锚定点，如8， 在前面的数组中，划分两块
 * <br/>
 * 4, 5, 6, 2,   7, 1, 10,   8
 *
 * @author xiao ji hao
 * @create 2021年07月11日 14:36:00
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] nums = new int[]{4, 5, 6, 2, 7, 1, 10, 8};
        sort(0, nums.length-1, nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void sort(int left, int right, int[] nums) {
        if(left>=right) {
            return ;
        }

        int partition = partition(left, right, nums);
        sort(left, partition-1,nums);
        sort(partition+1, right,nums);
    }

    public static int partition(int left, int right, int[]nums) {
        int i = left;
        int j = right-1;
        int point = nums[right];

        while (i<j) {
            //在移动右边的坐标，一直到右边的比point小为止
            while (j>left && nums[j]>point) {
                j--;
            }
            while(i<right && nums[i]<=point) {
                i++;
            }
            //当left>point  right<point时，交换两个数据
            if(i<j) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
                i++;
                j--;
            }
        }
        nums[right] = nums[i];
        nums[i] = point ;
        //此时，point的左边的数<point
        //point右边的数>point
        return i;
    }

}
