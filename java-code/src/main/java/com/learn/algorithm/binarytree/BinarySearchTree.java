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

}
