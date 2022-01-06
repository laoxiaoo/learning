package com.xiao.algorithm.link;

/**
 * 反转链表
 * <br>
 * a -> b -> c -> d
 * <br>
 * 取三个指正，当前需要移动curTmp指向b,per指向c
 * curTmp的next指向a（current）,
 * 向前移动， curTmp = per， current = curTmp然后循环
 * 一直到curTmp==null
 * @author laoxiao
 * @create 2021年12月31日 13:23:00
 */
public class reverseLinkedList {

     static class ListNode {
          int val;
          ListNode next;
         ListNode() {}
          ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }

    public static ListNode reverseList(ListNode head) {
         //反转过后的头结点
        ListNode current = head;
        ListNode curTmp= head.next;
        ListNode per = null;
        while (curTmp != null) {
            per = curTmp.next;
            curTmp.next = current;
            current = curTmp;
            curTmp = per;
        }
        head.next = null;
        return current;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        ListNode listNode1 = reverseList(listNode);
        System.out.println(listNode1);
    }

}
