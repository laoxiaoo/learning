package com.xiao.heap;

import java.util.Arrays;

/**
 * 最大堆
 * <p></p>
 * 使用数组的方式去模拟二叉树
 *
 * 1. 当前节点，父节点=index-1 /2
 * <br>
 * 2. 当前节点 左子树left = index*2+1  right=index*2+2
 *
 * @author xiao ji hao
 * @create 2021年11月24日 22:39:00
 */
public class MaximumHeap {
    //
    static int[] heap = new int[9];

    /**
     * 创建一个最大堆
     * @param num 传入对的数组
     * @param i 传入的第几个数
     */
    static void createMaxHeap(int num, int i) {
        heap[i] = num;
        //当前节点=最后一个数组
        int currentIndex = i;
        //一直循环到头部节点
        while (currentIndex!=0) {
            //左右节点和父节点对比
            int parentIndex = (currentIndex-1) >>1;
            if(heap[parentIndex] < heap[currentIndex]) {
                //如果父节点比其小，则交换数字，交换完后，父节点再和父父节点比较
                int tmp = heap[parentIndex];
                heap[parentIndex] = heap[currentIndex];
                heap[currentIndex] = tmp;
                currentIndex = parentIndex;
            } else {
                break;
            }
        }
        System.out.println(Arrays.toString(heap));
    }

    /**
     * 最大堆下沉
     * @param num
     */
    static void down(int num) {
        //进来一个数，比较，如果num< 根节点，那么进行替换，
        //然后根节点进行下沉
        if(num < heap[0]){
            heap[0] = num;
        } else {
            return;
        }
        //当前index
        int index = 0;
        //当前
        while (index <= heap.length-1 ) {
            int left = index*2+1;
            int right = index*2+2;

            if(right > heap.length-1) {
                break;
            }
            //需要替换的index
            int replaceIndex = 0;
            //需要拿子节点最大的和当前比较
            if(heap[left] < heap[right]) {
                replaceIndex = right;
            } else {
                replaceIndex = left;
            }
            if(heap[index] < heap[replaceIndex]) {
                //如果当前节点比子节点最大的节点要小，则进行交换
                int tmp = heap[replaceIndex];
                heap[replaceIndex] = heap[index];
                heap[index] = tmp;
            }
            index = replaceIndex;
        }
        System.out.println(Arrays.toString(heap));
    }

    public static void main(String[] args) {
        int[] a = {2, 3, 1, 4, 5, 9 , 10, 23, 6};
        for(int i=0; i<a.length; i++) {
            createMaxHeap(a[i], i);
        }
        down(7);
    }

}
