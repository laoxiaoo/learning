package com.xiao.algorithm.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU:  最近最少使用, 超过阈值，删除最少使用的数据，查询和删除应该都说0(1)
 * <br>
 * 采用 hash + 双向链表的结构
 * <br>
 * 即，查询的时候，通过hash查询，  删除的时候，删除链表里的数据，
 * <br>
 * 当有插入重复数据时，将当前数据删除，重新放入队列
 * <br>
 * 当有数据删除是，删除队列最前的数据
 * @author laoxiao
 * @create 2021年07月06日 00:14:00
 */
public class LRU {

    public static void main(String[] args) {
        MyLRU<Integer, Integer> lru = new MyLRU<>(3);
        lru.put(1, 1);
        lru.put(2, 2);
        lru.put(3, 3);
        lru.put(2, 2);
        lru.put(4, 4);
    }
}

class Node<K, V> {
    public V val;
    public K key;
    public Node<K, V> prev;
    public Node<K, V> next;
    public Node(K key, V val) {
        this.key = key;
        this.val = val;
    }
    public Node() {

    }
}

class DLinkQueue<K, V> {
    public Node<K, V> head;
    public Node<K,V> tail;
    public DLinkQueue() {
        head = new Node<>();
        tail = new Node<>();
        head.next = tail;
        head.prev = tail;
        tail.next = head;
        tail.prev = head;
    }
    public void addHead(Node<K, V> node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
    public void remove(Node<K,V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = null;
        node.prev = null;
    }

    public Node<K, V> getLast() {
        return this.tail.prev;
    }

}

class MyLRU<K, V> {
    Map<K, Node<K, V>> map = new HashMap<>();
    DLinkQueue<K, V> queue = new DLinkQueue<>();
    int cacheSize = 0;
    public MyLRU(int cacheSize) {
        this.cacheSize = cacheSize;
    }

    public void put(K key, V val) {
        Node<K, V> node;
        //当node数重复，则将数据前移，变成热点数据
        if(map.containsKey(key)) {
            node = map.get(key);
            queue.remove(node);
        } else {
            node = new Node<>(key, val);
            map.put(key, node);
        }
        queue.addHead(node);
        //数据超过阈值，则删除数据
        if(cacheSize < map.size()) {
            Node<K, V> last = queue.getLast();
            queue.remove(last);
            map.remove(key);
        }
    }


}
