package leetcode;

import java.util.HashMap;

/**
 * Created by zx on 2017/6/5.
 */
public class LRUCache {
    private int capacity;
    // 存值
    private HashMap<Integer, Node<Integer, Integer>> map;
    // 头部为最近访问
    private Node<Integer, Integer> head;
    // 尾部为优先移除
    private Node<Integer, Integer> tail;

    static class Node<K, V> {
        K key;
        V value;

        Node<K, V> prev;
        Node<K, V> next;

        public Node() {

        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
        head = new Node<>();
        tail = new Node<>();
        head.next = tail;
        tail.prev = head;
    }

    /**
     * head               prev   <->    this    <->    next
     * this <-> head      prev   <->    next
     *
     * @param key
     * @return
     */
    public int get(int key) {
        Node<Integer, Integer> node = map.get(key);
        if (node != null) {
            // 提升
            promote(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        Node<Integer, Integer> node = map.get(key);
        if (node == null) {
            // 如果没有：存入并提升 (判断是否已满)
            if (map.size() == capacity) {

                // 去掉 tail
                if (tail.prev != head) {
                    System.out.printf("已满，去掉 %d\n", tail.prev.key);
                    map.remove(tail.prev.key);
                    remove(tail.prev);
                }
            }

            node = new Node<>(key, value);
            map.put(key, node);
        }
        node.value = value; // update value
        promote(node);

    }

    private void promote(Node<Integer, Integer> node) {
        // 删除 this
        remove(node);

        // 插入到头后
        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;

    }

    private void remove(Node<Integer, Integer> node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        }
        if (node.next != null){
            node.next.prev = node.prev;
        }

    }


    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2 /* capacity */);

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4
    }
}
