package com.xiao.heap;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 最小堆
 * <p></p>
 * 数组的方式
 * <br>

 *
 * @author xiao ji hao
 * @create 2021年11月23日 20:53:00
 */
@Slf4j
public class BinaryHeap {

    private int[] node = new int[9];

    public void put(int index, int n) {
        node[index] = n;
    }

    public void build() {
        int size = node.length/2;
        for(int i=1; i<= size; i++) {
            down(i);
            System.out.println(Arrays.toString(node));
        }
    }

    public void down(int i) {
        //左子树
        int left = i<<1;
        //右子树
        int right = (i<<1)+1;

        if(right <= node.length-1 && node[left] > node[right]) {
            //左子树>右子树，则进行交换
            int tmp = node[left];
            node[left] = node[right];
            node[right] = tmp;
        }
        //左子树和父节点比较
        if(node[left] < node[i]) {
            int tmp = node[left];
            node[left] = node[i];
            node[i] = tmp;
        }
    }

    public static void main(String[] args) {
        //第一位0是舍弃的
        BinaryHeap binaryHeap = new BinaryHeap();
        binaryHeap.put(0,0);
        binaryHeap.put(1,1);
        binaryHeap.put(2, 2);
        binaryHeap.put(3, 3);
        binaryHeap.put(4, 4);
        binaryHeap.put(5, 5);
        binaryHeap.put(6, 6);
        binaryHeap.put(7, 7);
        binaryHeap.build();
    }
}
