package com.learn.algorithm.binarytree;

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

    public void preOrder() {
        preOrder0(root);
    }

    private void preOrder0(Node node) {
        if(node == null) {
            return;
        }
        preOrder0(node.left);
        PrintUtils.print(node);
        preOrder0(node.right);
    }

}
