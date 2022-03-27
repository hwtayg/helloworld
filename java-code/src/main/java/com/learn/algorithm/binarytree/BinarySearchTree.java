package com.learn.algorithm.binarytree;

import java.util.*;

public class BinarySearchTree<K extends Comparable<K>, V> {
    Node<K, V> root;

    public BinarySearchTree buildTree(K[] keys, V value) {
        for (K key : keys) {
            put(key, value);
        }
        return this;
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

    public Node<K, V> max() {
        return max(root);
    }

    private Node<K, V> max(Node<K, V> node) {
        if (node == null) {
            return null;
        }
        if (node.right != null) {
            return max(node.right);
        } else {
            return node;
        }
    }

    public Node<K,V> min() {
        return min(root);
    }

    private Node<K,V> min(Node<K, V> node) {
        if (node == null) {
            return null;
        }
        if (node.left != null) {
            return min(node.left);
        } else {
            return node;
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



    public int hight() {
        if(root == null) {
            return 0;
        } else {
            return hight(this.root);
        }
    }

    private int hight(Node<K, V> root) {
        return levelNum(root) - 1;
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

    public void delMin() {
        root = delMin(root);
    }

    private Node<K,V> delMin(Node<K, V> node) {
        if (node.left == null) {
            return node.right;
        } else {
            node.left = delMin(node.left);
            return node;
        }
    }

    public void delMax() {
        root = delMax(root);
    }

    private Node<K, V> delMax(Node<K, V> node) {
        if (node.right == null) {
            return node.left;
        } else {
            node.right = delMax(node.right);
            return node;
        }
    }

    public void delete(K key) {
        root = delete(root, key);
    }

    private Node<K, V> delete(Node<K, V> node, K key) {
        if(node == null) {
            return null;
        }
        int cmp = node.key.compareTo(key);
        if(cmp == 0) {
            if (node.left != null && node.right != null) {
                Node<K, V> t = node;
                Node<K, V> x = min(t.right);
                x.right = delMin(t.right);
                x.left = t.left;
                x.size = size(x.left) + size(x.right);
                return x;
            } else if (node.left != null && node.right == null) {
                return node.left;
            } else if(node.left == null && node.right != null ) {
                return node.right;
            } else {
                return null;
            }
        } else if (cmp < 0) {
            node.right = delete(node.right, key);
        } else {
            node.left = delete(node.left, key);
        }
        node.size = size(node.left) + size(node.right);
        return node;
    }

    public Queue<K>  keys(K lo, K hi) {
        Queue<K> queue = new LinkedList<K>();
        keys(root, lo, hi, queue);
        return queue;
    }

    private void keys(Node<K, V> node, K lo, K hi, Queue<K> queue) {
        if (node == null) {
            return;
        }
        int lcmp = node.key.compareTo(lo);
        int hcmp = node.key.compareTo(hi);
        if (lcmp > 0 && hcmp < 0) {
            keys(node.left, lo, hi, queue);
            queue.add(node.key);
            keys(node.right, lo, hi, queue);
        }else if(lcmp == 0) {
            queue.add(node.key);
            keys(node.right,lo,hi, queue);
        } else if (hcmp == 0){
            keys(node.left, lo, hi, queue);
            queue.add(node.key);
        }else if (hcmp > 0) {
            keys(node.left, lo, hi, queue);
        } else if (lcmp < 0) {
            keys(node.right, lo, hi, queue);
        }
    }

}


