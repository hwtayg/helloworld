package com.learn.algorithm.test;

import com.learn.algorithm.binarytree.BinarySearchTree;
import com.learn.algorithm.binarytree.PrintUtils;
import com.learn.algorithm.binarytree.TreePrint;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.Queue;


public class BinarySearchTreeTest {

    @Test
    public void testPut1() {
        BinarySearchTree<String, Integer> bt = new BinarySearchTree<String, Integer>();
        String[] strs = new String[]{"E", "B", "C", "D"};
        bt.buildTree(strs, 1);
        bt.preOrder();
    }

    @Test
    public void testGet1() {
        BinarySearchTree<String, Integer> bt = new BinarySearchTree<String, Integer>();
        String[] strs = new String[]{"E", "B", "C", "D"};
        bt.buildTree(strs, 1);
        Integer res = bt.get("C");
        Assert.assertTrue(res == 1);
    }

    @Test
    public void testGetNotFound() {
        BinarySearchTree<String, Integer> bt = new BinarySearchTree<String, Integer>();
        String[] strs = new String[]{"E", "B", "C", "D"};
        bt.buildTree(strs, 1);
        Integer res = bt.get("F");
        Assert.assertTrue(res == null);
    }

    @Test
    public void testSize() {
        BinarySearchTree<String, Integer> bt = new BinarySearchTree<String, Integer>();
        String[] strs = new String[]{"E", "B", "C", "D"};
        bt.buildTree(strs, 1);
        Assert.assertTrue(bt.size() == 4);
    }

    @Test
    public void testMaxMin() {
        BinarySearchTree<String, Integer> bt = new BinarySearchTree<String, Integer>();
        String[] strs = new String[]{"E", "B", "C", "D"};
        bt.buildTree(strs, 1);
        Assert.assertTrue(bt.max().key.equals("E"));
        Assert.assertTrue(bt.min().key.equals("B"));
    }

    @Test
    public void testFloor() {
        BinarySearchTree<String, Integer> bt = new BinarySearchTree<String, Integer>();
        String[] strs = new String[]{"E", "B", "C", "D"};
        bt.buildTree(strs, 1);
        Assert.assertTrue(bt.floor("E").equals("E"));
        Assert.assertTrue(bt.floor("F").equals("E"));
        Assert.assertTrue(bt.floor("D").equals("D"));
        Assert.assertTrue(bt.floor("A") == null);
    }

    @Test
    public void testCelling() {
        BinarySearchTree<String, Integer> bt = new BinarySearchTree<String, Integer>();
        String[] strs = new String[]{"E", "B", "C", "D"};
        bt.buildTree(strs, 1);
        Assert.assertTrue(bt.celling("E").equals("E"));
        Assert.assertTrue(bt.celling("F") == null);
        Assert.assertTrue(bt.celling("D").equals("D"));
        Assert.assertTrue(bt.celling("A").equals("B"));
    }

    @Test
    public void testSelect() {
        BinarySearchTree<String, Integer> bt = new BinarySearchTree<String, Integer>();
        String[] strs = new String[]{"E", "B", "C", "D"};
        bt.buildTree(strs, 1);
        Assert.assertTrue(bt.select(0).equals("B"));
        Assert.assertTrue(bt.select(2).equals("D"));
        Assert.assertTrue(bt.select(3).equals("E"));
        Assert.assertTrue(bt.select(4) == null);
    }

    @Test
    public void testRank() {
        BinarySearchTree<String, Integer> bt = new BinarySearchTree<String, Integer>();
        String[] strs = new String[]{"E", "B", "C", "D"};
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
        String[] strs = new String[]{"E", "B", "C", "D"};
        TreePrint ptr = new TreePrint(bt.buildTree(strs, 1));
        ptr.printTree();
    }

    @Test
    public void testHight() {
        BinarySearchTree<String, Integer> bt = new BinarySearchTree<String, Integer>();
        String[] strs = new String[]{"E", "F", "C"};
        bt.buildTree(strs, 1);
        Assert.assertTrue(bt.hight() == 1);
    }


    @Test
    public void testPrint() {
        BinarySearchTree<String, Integer> bt = new BinarySearchTree<String, Integer>();
        String[] strs = new String[]{"E", "F", "C", "K", "J", "I", "H", "G"};

        TreePrint ptr = new TreePrint(bt.buildTree(strs, 1));
        ptr.printTree();
        ptr.printTree1();
        ptr.printTree2();
    }

    @Test
    public void testPrintGood() {
        BinarySearchTree<String, Integer> bt = new BinarySearchTree<String, Integer>();
        String[] strs = new String[]{"E", "F", "C"};

        TreePrint ptr = new TreePrint(bt.buildTree(strs, 1));
        ptr.printTree();
        ptr.printTree1();
        ptr.printTree3();
    }

    @Test
    public void testDelMinMax() {
        BinarySearchTree<String, Integer> bt = new BinarySearchTree<String, Integer>();
        String[] strs = new String[]{"E", "F", "C", "K", "J", "I", "H", "G"};

        BinarySearchTree binarySearchTree = bt.buildTree(strs, 1);
        TreePrint ptr = new TreePrint(binarySearchTree);
        ptr.printTree3();
        binarySearchTree.delMin();
        ptr.printTree3();
        binarySearchTree.delMax();
        ptr.printTree3();
    }

    @Test
    public void testDelete() {
        BinarySearchTree<Character, Integer> tree = new BinarySearchTree<Character, Integer>();
        tree.buildTree(new Character[]{'D', 'B', 'J', 'C', 'F', 'H', 'A', 'G', 'E', 'I'}, 1);
        TreePrint print = new TreePrint(tree);
        print.printTree3();
        tree.delete('F');
        print.printTree3();
    }

    @Test
    public void testRange() {
        BinarySearchTree<Character, Integer> tree = new BinarySearchTree<Character, Integer>();
        tree.buildTree(new Character[]{'D', 'B', 'J', 'C', 'F', 'H', 'A', 'G', 'E', 'I'}, 1);
        TreePrint print = new TreePrint(tree);
        print.printTree3();
        Queue<Character> queue = tree.keys('E', 'I');
        Assert.assertTrue(queue.size() == 5);
        Queue<Character> queue1 = tree.keys('C', 'E');
        Assert.assertTrue(queue1.size() == 3);
        Queue<Character> queue2 = tree.keys('C', 'K');
        Assert.assertTrue(queue2.size() == 8);

    }

}
