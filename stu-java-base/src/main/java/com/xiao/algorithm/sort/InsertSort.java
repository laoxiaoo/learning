package com.xiao.algorithm.sort;

import java.util.Arrays;

/**
 * 插入排序
 *  <ul>
 * 将最左边看做是已经排好序的数组，
 * <p>
 *  从最右边找出一个数，安装顺序插入左边的数组中
 * @author xiao ji hao
 * @create 2021年06月29日 11:40:00
 */
public class InsertSort {


    public static void main(String[] args) {
        int[] sort = sort(new int[]{4, 5, 6, 2, 7, 1, 10, 8});
        System.out.println(Arrays.toString(sort));
    }

    public static int[] sort(int[] nums) {
        for(int i=1; i<nums.length; i++) {
            int tmp = nums[i];
            int index=i;
            //当走到index=3时，此刻需要将数组往后移动
            //（将index=3的数提出来，别的数先覆盖他，tmp数在前面的位置找到适合自己的位置）
            for(int j=i-1; j>=0&& nums[j]>tmp; j--) {
                nums[j+1] = nums[j];
                index = j;
            }
            nums[index] = tmp;
        }
        return nums;
    }
}
