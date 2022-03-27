package com.learn.algorithm.binarytree;

import java.util.Objects;

public class Node<K extends Comparable<K>,V> {
    public K key;
    public V value;
    public int size;
    public Node<K, V> left;
    public Node<K, V> right;

    public Node(K key, V vlaue, int size, Node left, Node right) {
        this.key = key;
        this.value = vlaue;
        this.size = size;
        this.left = left;
        this.right = right;
    }
}
