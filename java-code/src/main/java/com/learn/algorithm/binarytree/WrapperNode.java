package com.learn.algorithm.binarytree;

public class WrapperNode<K extends Comparable<K>, V> {
    Node<K, V> node;
    int leftSpaceNum;
    // root是0层
    int level;
    WrapperNode(Node<K, V> node, int leftSpaceNum) {
        this.node = node;
        this.leftSpaceNum = leftSpaceNum;
    }

    WrapperNode(Node<K, V> node, int leftSpaceNum, int level) {
        this.node = node;
        this.leftSpaceNum = -1;
        this.level = level;
    }
}