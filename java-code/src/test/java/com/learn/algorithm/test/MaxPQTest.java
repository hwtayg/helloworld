package com.learn.algorithm.test;

import com.learn.algorithm.binarytree.MaxPQ;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public  class   MaxPQTest {

    @Test
    public void test1() {
        MaxPQ pq = new MaxPQ(5);
        pq.insert(3);
        pq.insert(5);
        pq.insert(1);
        pq.insert(0);
        pq.insert(2);
        while (!pq.isEmpty()) {
            System.out.println(pq.delMax());
        }
    }


    @Test
    public void test2() {
        MaxPQ pq = new MaxPQ(5);
        pq.insert(3);
        pq.insert(-5);
        pq.insert(1);
        pq.insert(0);
        pq.insert(0);
        while (!pq.isEmpty()) {
            System.out.println(pq.delMax());
        }
    }

    @Test
    public void test3() {
        MaxPQ pq = new MaxPQ(1000);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < 1000; i++) {
            int v = (int) (Math.random() * 1000) - 500;
            max = Math.max(max, v);
            pq.insert(v);
        }
        Assert.assertTrue(max == pq.max());
        Assert.assertTrue(pq.size() == 1000);
    }

    @Test
    public void test4() {
//        int[] arr = {Integer.MIN_VALUE, 1,3,2};
        int[] arr = {Integer.MIN_VALUE, 3, 1, 4, -1, 5, 6, 0};
        MaxPQ.sort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(Integer.toBinaryString(-1));

    }
}
