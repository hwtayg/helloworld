package com.learn.algorithm.test;

import com.learn.algorithm.binarytree.BinarySearchTree;

import java.util.HashMap;
import java.util.Map;

public class BinarySearchTreeTest {

    public static void main(String[] args) {
        BinarySearchTreeTest test = new BinarySearchTreeTest();
        test.test1();
    }

    private void test1() {
        BinarySearchTree<String, Integer> bt = new BinarySearchTree<String, Integer>();
        String[] strs = new String[] {"E", "B", "C", "D"};
        bt.buildTree(strs, 1);
        bt.preOrder();
    }
}
