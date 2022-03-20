package com.learn.algorithm.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinarySearchTree<K extends Comparable<K>, V> {
    Node<K, V> root;

    public void buildTree(K[] keys, V value) {
        for (K key : keys) {
            put(key, value);
        }
    }

    public void put(K key, V vlaue) {
        root = put(root, key, vlaue);
    }

    private Node put(Node<K, V> node, K key, V value) {
        if (node == null) {
            node = new Node<K, V>(key, value, 1, null, null);
            return node;
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            node.value = value;
            return node;
        } else if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else {
            node.right = put(node.right, key, value);
        }
        node.size = node.size + 1;
        return node;
    }

    public V get(K key) {
        return get(root, key);
    }

    private V get(Node<K, V> node, K key) {

        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return node.value;
        } else if (cmp < 0) {
            return get(node.left, key);
        } else {
            return get(node.right, key);
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node<K, V> node) {
        if (node == null) {
            return 0;
        } else {
            return node.size;
        }
    }

    public K max() {
        return max(root);
    }

    private K max(Node<K, V> node) {
        if (node == null) {
            return null;
        }
        if (node.right != null) {
            return max(node.right);
        } else {
            return node.key;
        }
    }

    public K min() {
        return min(root);
    }

    private K min(Node<K, V> node) {
        if (node == null) {
            return null;
        }
        if (node.left != null) {
            return min(node.left);
        } else {
            return node.key;
        }
    }

    public K floor(K key) {
        return floor(root, key);
    }

    private K floor(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = node.key.compareTo(key);
        if (cmp == 0) {
            return node.key;
        } else if (cmp > 0) {
            return floor(node.left, key);
        } else {
            K r = floor(node.right, key);
            return r == null ? node.key : r;
        }
    }

    public K celling(K key) {
        return celling(root, key);
    }

    private K celling(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = node.key.compareTo(key);
        if (cmp == 0) {
            return node.key;
        } else if (cmp < 0) {
            return celling(node.right, key);
        } else {
            K l = celling(node.left, key);
            return  l == null ? node.key : l;
        }
    }

    public K select(int k) {
        return select(root, k);
    }

    private K select(Node<K, V> node, int k) {
        if (node == null) {
            return null;
        }
        int sizeLeft = size(node.left);
        if (sizeLeft == k) {
            return node.key;
        } else if (sizeLeft > k) {
            return select(node.left, k);
        } else {
            return select(node.right, k - sizeLeft - 1);
        }
    }

    public int rank(K key) {
        return rank(root, key);
    }

    private int rank(Node<K, V> node, K key) {
        if (node == null) {
            return -1;
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return size(node.left);
        } else if(cmp < 0) {
            return rank(node.left, key);
        } else {
            int r = rank(node.right, key);
            return r >= 0 ? size(node.left) + 1 + r : r;
        }
    }

    public void preOrder() {
        preOrder0(root);
    }

    private void preOrder0(Node node) {
        if (node == null) {
            return;
        }
        preOrder0(node.left);
        PrintUtils.print(node);
        preOrder0(node.right);
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


    private void fillSpace(StringBuilder sb, int insertPos, String value) {
        int length = sb.length();
        if (length > insertPos) {
            sb.replace(insertPos,insertPos+1, value.toString());
        } else if (length == insertPos){
            sb.append(value);
        } else {
            for(int i = 0; i < insertPos - length; i++) {
                sb.append(" ");
            }
            sb.append(value);
        }
    }


    public int hight() {
        int level = 0;
        if (root == null) {
            return level - 1;
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
        return level - 1;
    }

    private class WrapperNode<K extends Comparable<K>, V> {
        Node<K, V> node;
        int leftSpaceNum;
        WrapperNode(Node<K, V> node, int leftSpaceNum) {
            this.node = node;
            this.leftSpaceNum = leftSpaceNum;
        }
    }
}


