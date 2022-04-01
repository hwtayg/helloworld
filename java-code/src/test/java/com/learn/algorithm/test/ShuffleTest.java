package com.learn.algorithm.test;

import com.learn.algorithm.binarytree.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShuffleTest {

    @Test
    public void shuffle() {

        List<Character> characters = new ArrayList<Character>();
        for (char i = 'A'; i < 'A' + 26; i++) {
            characters.add(i);
        }
        Collections.shuffle(characters);
        BinarySearchTree<Character, Integer> tree = new BinarySearchTree<Character, Integer>();
        tree.buildTree(characters.toArray(new Character[characters.size()]), 1);
        //tree.buildTree(new Character[]{'D', 'B', 'J', 'C', 'F', 'H', 'A', 'G', 'E', 'I'}, 1);
        TreePrint ptr = new TreePrint(tree);
        ptr.printTree();
        ptr.printTree1();
        ptr.printTree2();
        ptr.printTree3();
        tree.preOrder();
    }


    @Test
    public void shuffleRBTree() {

        List<Character> characters = new ArrayList<Character>();
        for (char i = 'A'; i < 'A' + 26; i++) {
            characters.add(i);
        }
        Collections.shuffle(characters);
        RBTree<Character, Integer> tree = new RBTree<Character, Integer>();
        tree.buildTree(characters.toArray(new Character[characters.size()]), 1);
        TreePrint ptr = new TreePrint(tree);
        ptr.printTree3();
        ptr.printTreeWithMark(true);
    }

}
