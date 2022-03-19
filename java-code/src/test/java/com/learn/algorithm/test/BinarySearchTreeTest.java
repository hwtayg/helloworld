package com.learn.algorithm.test;

import com.learn.algorithm.binarytree.BinarySearchTree;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


public class BinarySearchTreeTest {

    @Test
    public void testPut1() {
        BinarySearchTree<String, Integer> bt = new BinarySearchTree<String, Integer>();
        String[] strs = new String[] {"E", "B", "C", "D"};
        bt.buildTree(strs, 1);
        bt.preOrder();
    }

    @Test
    public void testGet1() {
        BinarySearchTree<String, Integer> bt = new BinarySearchTree<String, Integer>();
        String[] strs = new String[] {"E", "B", "C", "D"};
        bt.buildTree(strs, 1);
        Integer res = bt.get("C");
        Assert.assertTrue(res == 1);
    }

    @Test
    public void testGetNotFound() {
        BinarySearchTree<String, Integer> bt = new BinarySearchTree<String, Integer>();
        String[] strs = new String[] {"E", "B", "C", "D"};
        bt.buildTree(strs, 1);
        Integer res = bt.get("F");
        Assert.assertTrue(res == null);
    }

    @Test
    public void testSize() {
        BinarySearchTree<String, Integer> bt = new BinarySearchTree<String, Integer>();
        String[] strs = new String[] {"E", "B", "C", "D"};
        bt.buildTree(strs, 1);
        Assert.assertTrue(bt.size() == 4);
    }

    @Test
    public void testMaxMin() {
        BinarySearchTree<String, Integer> bt = new BinarySearchTree<String, Integer>();
        String[] strs = new String[] {"E", "B", "C", "D"};
        bt.buildTree(strs, 1);
        Assert.assertTrue(bt.max().equals("E"));
        Assert.assertTrue(bt.min().equals("B"));
    }

    @Test
    public void testFloor() {
        BinarySearchTree<String, Integer> bt = new BinarySearchTree<String, Integer>();
        String[] strs = new String[] {"E", "B", "C", "D"};
        bt.buildTree(strs, 1);
        Assert.assertTrue(bt.floor("E").equals("E"));
        Assert.assertTrue(bt.floor("F").equals("E"));
        Assert.assertTrue(bt.floor("D").equals("D"));
        Assert.assertTrue(bt.floor("A") == null);
    }

    @Test
    public void testCelling() {
        BinarySearchTree<String, Integer> bt = new BinarySearchTree<String, Integer>();
        String[] strs = new String[] {"E", "B", "C", "D"};
        bt.buildTree(strs, 1);
        Assert.assertTrue(bt.celling("E").equals("E"));
        Assert.assertTrue(bt.celling("F") == null);
        Assert.assertTrue(bt.celling("D").equals("D"));
        Assert.assertTrue(bt.celling("A").equals("B"));
    }
}
