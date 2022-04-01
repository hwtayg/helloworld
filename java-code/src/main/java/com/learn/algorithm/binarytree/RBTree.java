package com.learn.algorithm.binarytree;

public class RBTree<K extends Comparable<K>, V> extends BinarySearchTree<K, V> {

    protected Node rotateLeft(Node<K, V> node) {
        Node h = node;
        Node x = node.right;
        h.right = x.left;
        x.left = h;

        x.color = h.color;
        h.color = Color.RED;

        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    protected Node rotateRight(Node<K, V> node) {
        Node h = node;
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = Color.RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    protected void flipColors(Node h) {
        h.left.color = Color.BLACK;
        h.right.color = Color.BLACK;
        h.color = Color.RED;
    }

    @Override
    Node put(Node<K, V> node, K key, V value) {

        if (node == null) {
            node = new Node<K, V>(key, value, 1, null, null);
            node.color = Color.RED;
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

        if (Node.isRed(node.right)) {
            node = rotateLeft(node);
        }
        if (node.left != null && Node.isRed(node.left) && Node.isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (Node.isRed(node.left) && Node.isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }
}
