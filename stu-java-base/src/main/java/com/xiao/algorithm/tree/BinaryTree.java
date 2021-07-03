package com.xiao.algorithm.tree;

/**
 * @author xiao ji hao
 * @create 2021年06月30日 20:47:00
 */
public class BinaryTree {
    static class Node<T> {
        T val;
        Node<T> left;
        Node<T> right;
        public Node(T val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Node<Integer> node1 = new Node<>(1);
        Node<Integer> node2 = new Node<>(2);
        Node<Integer> node3 = new Node<>(3);
        Node<Integer> node4 = new Node<>(4);
        Node<Integer> node5 = new Node<>(5);
        Node<Integer> node6 = new Node<>(6);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        System.out.print("先序遍历：");
        firstOrder(node1);
        System.out.print("\n中序遍历：");
        inorder(node1);
    }


    /**
     * 先序列
     * @param node
     */
    public static void firstOrder(Node<Integer> node) {
        if(node == null) {
            return;
        }
        System.out.print(node.val + ",");
        firstOrder(node.left);
        firstOrder(node.right);
    }

    public static void inorder(Node<Integer> node) {
        if(node == null) {
            return;
        }
        inorder(node.left);
        System.out.print(node.val + ",");
        inorder(node.right);
    }
}


