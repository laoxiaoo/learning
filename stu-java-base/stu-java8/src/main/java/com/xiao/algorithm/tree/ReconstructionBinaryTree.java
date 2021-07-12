package com.xiao.algorithm.tree;

import java.util.Arrays;

/**
 * 重建二叉树
 * <p/>
 * 如：先序遍历：[1,2,3,4,5,6,7]
 * <p/>
 *    中序遍历 [3,2,4,1,6,5,7]
 * <p/>
 *    可以看到，1是根节点， 那么 1 的左子树就是324（从中序可以得知）
 *    那么 234 可以看成是一个新的先序列，324 可以看成是一个新的中序
 * <p/>
 *    先序： 1 的后三位是左子树，其他是右子树
 *    中序： 1 的左边是左子树，其他是右子树
 *
 * @author xiao ji hao
 * @create 2021年06月30日 23:31:00
 */
public class ReconstructionBinaryTree {

    public static BinaryTree.Node<Integer> reConstructBinaryTree(int [] pre, int [] in) {

        int firstVal = pre[0];
        BinaryTree.Node<Integer> integerNode = new BinaryTree.Node<>(firstVal);
        //寻找出左子树
        int i=0;
        for(; i<in.length; i++) {
            if(firstVal == in[i]) {
                break;
            }
        }
        int[] leftPre = Arrays.copyOfRange(pre, 1, 1+i);
        int[] leftIn = Arrays.copyOfRange(in, 0, i);




        return integerNode;
    }

    public static void main(String[] args) {
        reConstructBinaryTree(new int[] {1,2,3,4,5,6,7}, new int[] {3,2,4,1,6,5,7});
    }
}
