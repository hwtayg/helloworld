package com.learn.algorithm.test;

import com.learn.algorithm.binarytree.BinarySearchTree;
import com.learn.algorithm.binarytree.TreePrint;
import org.junit.Assert;
import org.junit.Test;



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

    @Test
    public void testSelect() {
        BinarySearchTree<String, Integer> bt = new BinarySearchTree<String, Integer>();
        String[] strs = new String[] {"E", "B", "C", "D"};
        bt.buildTree(strs, 1);
        Assert.assertTrue(bt.select(0).equals("B"));
        Assert.assertTrue(bt.select(2).equals("D"));
        Assert.assertTrue(bt.select(3).equals("E"));
        Assert.assertTrue(bt.select(4) == null);
    }

    @Test
    public void testRank() {
        BinarySearchTree<String, Integer> bt = new BinarySearchTree<String, Integer>();
        String[] strs = new String[] {"E", "B", "C", "D"};
        bt.buildTree(strs, 1);
        Assert.assertTrue(bt.rank("B") == 0);
        Assert.assertTrue(bt.rank("C") == 1);
        Assert.assertTrue(bt.rank("E") == 3);
        Assert.assertTrue(bt.rank("A") == -1);
        Assert.assertTrue(bt.rank("F") == -1);
    }

    @Test
    public void testPrintTree() {
        BinarySearchTree<String, Integer> bt = new BinarySearchTree<String, Integer>();
        String[] strs = new String[] {"E", "B", "C", "D"};
        TreePrint ptr = new TreePrint(bt.buildTree(strs, 1));
        ptr.printTree();
    }

    @Test
    public void testHight() {
        BinarySearchTree<String, Integer> bt = new BinarySearchTree<String, Integer>();
        String[] strs = new String[] {"E", "F", "C"};
        bt.buildTree(strs, 1);
        Assert.assertTrue(bt.hight() == 1);
    }


    @Test
    public void testPrint() {
        BinarySearchTree<String, Integer> bt = new BinarySearchTree<String, Integer>();
        String[] strs = new String[] {"E", "F", "C", "K","J","I", "H", "G"};

        TreePrint ptr = new TreePrint(bt.buildTree(strs, 1));
        ptr.printTree();
        ptr.printTree1();
        ptr.printTree2();
    }

    @Test
    public void testPrintGood() {
        BinarySearchTree<String, Integer> bt = new BinarySearchTree<String, Integer>();
        String[] strs = new String[] {"E", "F", "C"};

        TreePrint ptr = new TreePrint(bt.buildTree(strs, 1));
        ptr.printTree();
        ptr.printTree1();
        ptr.printTree3();
    }
}
