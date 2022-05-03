package com.learn.algorithm.binarytree;

import java.util.*;

public class TreePrint<K extends Comparable<K>, V> {

    private BinarySearchTree<K, V> binarySearchTree;
    private Node<K, V> root;

    public TreePrint(BinarySearchTree<K, V> binarySearchTree) {
        this.root = binarySearchTree.root;
        this.binarySearchTree = binarySearchTree;
    }

    /**
     * printTree，左右子树可能会重叠互相覆盖
     */
    public void printTree() {
        if (root == null) {
            return;
        }
        int rootPos = findRootPos();
        int rootSpaceCount =0;
        if (rootPos < 0) {
            rootSpaceCount = (0-rootPos) * 2;
        }

        LinkedList<WrapperNode<K,V>> levelVisitQueue = new LinkedList<WrapperNode<K, V>>();
        List<StringBuilder> levelPrint = new ArrayList<StringBuilder>();

        WrapperNode wrapperNode = new WrapperNode(root, rootSpaceCount);

        levelVisitQueue.add(wrapperNode);
        while (!levelVisitQueue.isEmpty()) {
            int size = levelVisitQueue.size();
            StringBuilder sb = new StringBuilder();
            StringBuilder jointSb = new StringBuilder();
            for (int i = 0; i < size; i++) {
                WrapperNode<K,V> wrapper = levelVisitQueue.remove();
                fillSpace(sb, wrapper.leftSpaceNum, wrapper.node.key.toString());
                if (wrapper.node.left != null) {
                    fillSpace(jointSb, wrapper.leftSpaceNum - 1, "/");
                    WrapperNode newLeft = new WrapperNode(wrapper.node.left, wrapper.leftSpaceNum - 2);
                    levelVisitQueue.add(newLeft);
                }
                if (wrapper.node.right != null) {
                    fillSpace(jointSb, wrapper.leftSpaceNum + 1, "\\");
                    WrapperNode newR = new WrapperNode(wrapper.node.right, wrapper.leftSpaceNum + 2);
                    levelVisitQueue.add(newR);
                }
            }
            levelPrint.add(sb);
            levelPrint.add(jointSb);
        }

        for (StringBuilder sb : levelPrint) {
            PrintUtils.printS(sb.toString());
        }
    }

    private int levelNum(Node<K, V> root) {
        int level = 0;
        if (root == null) {
            return level;
        }
        LinkedList<Node<K, V>> queue = new LinkedList<Node<K, V>>();
        queue.add(root);
        while (!queue.isEmpty()) {
            level = level + 1;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node<K, V> node = queue.remove();
                if(node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return level;
    }

    /**
     * printTree1 缩进不合理，左右子树仍然会重叠
     */
    public void printTree1() {
        if (root == null) {
            return;
        }

        int leftLevel = levelNum(root.left);
        int rightLevel = levelNum(root.right);

        int rootPos = Math.max(leftLevel, rightLevel);
        int rootSpaceCount = rootPos * 2 + 1;


        LinkedList<WrapperNode<K,V>> levelVisitQueue = new LinkedList<WrapperNode<K, V>>();
        List<StringBuilder> levelPrint = new ArrayList<StringBuilder>();

        WrapperNode wrapperNode = new WrapperNode(root, rootSpaceCount);

        levelVisitQueue.add(wrapperNode);
        while (!levelVisitQueue.isEmpty()) {
            int size = levelVisitQueue.size();
            StringBuilder sb = new StringBuilder();
            StringBuilder jointSb = new StringBuilder();
            for (int i = 0; i < size; i++) {
                WrapperNode<K,V> wrapper = levelVisitQueue.remove();
                sb = makeLine(sb, wrapper.leftSpaceNum, wrapper.leftSpaceNum, wrapper.node.key.toString());

                if (wrapper.node.left != null) {
                    sb = makeLine(sb, wrapper.leftSpaceNum/2 + 1, wrapper.leftSpaceNum -1, "-");
                    jointSb = makeLine(jointSb, wrapper.leftSpaceNum/2 ,wrapper.leftSpaceNum/2, "|");
                    WrapperNode newLeft = new WrapperNode(wrapper.node.left, wrapper.leftSpaceNum/2);
                    levelVisitQueue.add(newLeft);
                }

                if (wrapper.node.right != null) {
                    sb = makeLine(sb, wrapper.leftSpaceNum + 1, wrapper.leftSpaceNum + wrapper.leftSpaceNum / 2, "-");
                    jointSb = makeLine(jointSb, wrapper.leftSpaceNum + wrapper.leftSpaceNum/2 + 1, wrapper.leftSpaceNum + wrapper.leftSpaceNum/2 + 1, "|");
                    WrapperNode newR = new WrapperNode(wrapper.node.right, wrapper.leftSpaceNum + wrapper.leftSpaceNum/2 + 1 + 1);
                    levelVisitQueue.add(newR);
                }
            }
            levelPrint.add(sb);
            levelPrint.add(jointSb);
        }

        for (StringBuilder sb : levelPrint) {
            PrintUtils.printS(sb.toString());
        }
    }

    /**
     * 对二叉树进行打印，先查找root的位置
     *
     * @return 树的root左边需要补充的空格树
     */
    public int findRootPos() {
        return findRootPos(root, 0);
    }

    private int findRootPos(Node<K, V> node, int pos) {
        if (node == null) {
            return pos;
        }
        int leftP = pos;
        int posR = pos;
        if (node.left != null) {
            leftP = findRootPos(node.left, pos - 1);
        }
        if (node.right != null) {
            posR = findRootPos(node.right, pos + 1);
        }
        return Math.min(leftP, posR);
    }



    public StringBuilder makeLine(StringBuilder sb, int startPos, int endPos, String value){
        if (endPos < startPos) {
            endPos = startPos;
        }
        int len = sb.length();
        int fillLen = endPos + 1 - len;
        for(int i = 0; i < fillLen; i++) {
            sb.append(" ");
        }
        StringBuilder newSb = new StringBuilder();
        for (int i = 0;i < sb.length(); i++) {
            if (i >= startPos && i <= endPos) {
                newSb.append(value);
            } else {
                newSb.append(sb.charAt(i));
            }
        }
        return newSb;
    }

    private void fillSpace(StringBuilder sb, int insertPos, String value) {
        int length = sb.length();
        if (length > insertPos) {
            sb.replace(insertPos,insertPos+1, value);
        } else if (length == insertPos){
            sb.append(value);
        } else {
            for(int i = 0; i < insertPos - length; i++) {
                sb.append(" ");
            }
            sb.append(value);
        }
    }

    /**
     * printTree2减缓左右子树重叠，但仍然不可避免
     */
    public void printTree2() {
        if (root == null) {
            return;
        }


        int leftLevel = levelNum(root.left);
        int rightLevel = levelNum(root.right);

        int rootPos = Math.max(leftLevel, rightLevel);
        int rootSpaceCount = rootPos * 2 + 1;
        LinkedList<WrapperNode<K,V>> levelVisitQueue = new LinkedList<WrapperNode<K, V>>();
        List<StringBuilder> levelPrint = new ArrayList<StringBuilder>();

        WrapperNode wrapperNode = new WrapperNode(root, rootSpaceCount);

        levelVisitQueue.add(wrapperNode);
        while (!levelVisitQueue.isEmpty()) {
            int size = levelVisitQueue.size();
            StringBuilder sb = new StringBuilder();
            StringBuilder jointSb = new StringBuilder();
            for (int i = 0; i < size; i++) {
                WrapperNode<K,V> wrapper = levelVisitQueue.remove();
                sb = makeLine(sb, wrapper.leftSpaceNum, wrapper.leftSpaceNum, wrapper.node.key.toString());
                int leftLeveltmp = levelNum(wrapper.node.left);
                int rightLeveltmp = levelNum(wrapper.node.right);

                int expand = Math.max(leftLeveltmp, rightLeveltmp);

                if (wrapper.node.left != null) {
                    sb = makeLine(sb, wrapper.leftSpaceNum - expand, wrapper.leftSpaceNum -1, "-");
                    jointSb = makeLine(jointSb, wrapper.leftSpaceNum - expand - 1 ,wrapper.leftSpaceNum - expand - 1, "|");
                    WrapperNode newLeft = new WrapperNode(wrapper.node.left, wrapper.leftSpaceNum - expand - 1);
                    levelVisitQueue.add(newLeft);
                }

                if (wrapper.node.right != null) {
                    sb = makeLine(sb, wrapper.leftSpaceNum + 1, wrapper.leftSpaceNum + expand, "-");
                    jointSb = makeLine(jointSb, wrapper.leftSpaceNum + expand + 1, wrapper.leftSpaceNum + expand + 1, "|");
                    WrapperNode newR = new WrapperNode(wrapper.node.right, wrapper.leftSpaceNum + expand + 1);
                    levelVisitQueue.add(newR);
                }
            }
            levelPrint.add(sb);
            levelPrint.add(jointSb);
        }

        for (StringBuilder sb : levelPrint) {
            PrintUtils.printS(sb.toString());
        }
    }

    /**
     * 解决重叠问题
     */
    public void printTree3() {
        printTreeWithMark(false);
    }

    public void printTreeWithMark(boolean markRed) {

        if(root == null) {
            return;
        }
        LinkedList<Node<K,V>> queue = new LinkedList<Node<K, V>>();
        travel(root, queue);

        int col = queue.size();
        int row = binarySearchTree.hight() * 2 + 1;
        String[][] printArr = new String[row][col];
        for (int i = 0; i < row; i++) {
            Arrays.fill(printArr[i], " ");
        }

        LinkedList<WrapperNode<K, V>> travelQ = new LinkedList<WrapperNode<K, V>>();
        travelQ.add(new WrapperNode<K, V>(root, -1, 0));
        while (!travelQ.isEmpty()) {
            WrapperNode<K, V> wrapperNode = travelQ.remove();
            int rowIdx = wrapperNode.level;
            int colIdx = findIdxInQueue(wrapperNode.node, queue);
            if (markRed && Node.isRed(wrapperNode.node)) {
                printArr[rowIdx][colIdx] = "*";
            } else {
                printArr[rowIdx][colIdx] = wrapperNode.node.key.toString();
            }
            Node<K, V> left = wrapperNode.node.left;
            if (left != null) {
                int leftColId = findIdxInQueue(left, queue);
                for (int i = leftColId; i < colIdx; i++) {
                    printArr[rowIdx][i] = "-";
                }
                printArr[rowIdx+1][leftColId] = "|";
                travelQ.add(new WrapperNode<K, V>(left, -1,rowIdx + 2));
            }
            Node<K, V> right = wrapperNode.node.right;
            if(right != null) {
                int rightColIdx = findIdxInQueue(right, queue);
                for (int i = colIdx+1; i <= rightColIdx; i++) {
                    printArr[rowIdx][i] = "-";
                }
                printArr[rowIdx+1][rightColIdx] = "|";
                travelQ.add(new WrapperNode<K, V>(right, -1,rowIdx + 2));
            }
        }

        for(int i = 0; i < row; i++) {
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < col; j++) {
                sb.append(printArr[i][j]);
            }
            PrintUtils.printS(sb.toString());
        }
    }

    private int findIdxInQueue(Node<K, V> node, LinkedList<Node<K, V>> queue) {

        for(int i=0; i< queue.size(); i++) {
            if(node.key.equals(queue.get(i).key)) {
                return i;
            }
        }
        return -1;
    }


    private void travel(Node<K, V> node, Queue<Node<K,V>> queue) {
        if (node == null) {
            return;
        }
        if(node.left != null) {
            travel(node.left, queue);
        }
        queue.add(node);
        if(node.right != null) {
            travel(node.right, queue);
        }
    }
}
