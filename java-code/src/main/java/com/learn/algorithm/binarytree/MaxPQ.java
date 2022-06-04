package com.learn.algorithm.binarytree;


public class MaxPQ {

    private Integer[] arr;

    private int N;

    public MaxPQ(int size) {
        arr = new Integer[size + 1];
        N = 0;
    }

    private boolean less(int i, int j) {
        return arr[i].compareTo(arr[j]) < 0;
    }

    private void exch(int i, int j) {
        Integer k = arr[i];
        arr[i] = arr[j];
        arr[j] = k;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }

    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (2 * k < N && less(j, j + 1)) j++;
            if (!less(k, j)) break;

            exch(k, j);
            k = j;
        }
    }

    public void insert(Integer v) {
        assert N + 1 < arr.length;
        arr[N + 1] = v;
        swim(N + 1);
        N++;
    }

    public Integer max() {
        return arr[1];
    }

    public Integer delMax() {
        int max = arr[1];
        arr[1] = arr[N];
        arr[N] = 0;
        N--;
        sink(1);
        return max;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private static void exch(int[] arr, int i, int j) {
        int v = arr[i];
        arr[i] = arr[j];
        arr[j] = v;
    }

    private static void sink(int[] arr, int k, int N) {

        while (2*k <= N) {
            int j = k * 2;
            if (2*k < N && less(arr, j , j+1)) j = j + 1;
            if (!less(arr, k, j)) break;
            exch(arr, k, j);
            k = j;
        }
    }


    private static boolean less(int[] arr, int i, int j) {
        return arr[i] - arr[j] < 0;
    }

    public static void sort(int[] arr) {

        // build heap
        int N = arr.length - 1;
        for (int k = N / 2; k >= 1; k--) {
            sink(arr, k, N);
        }

        //sort
        while (N >= 1) {
            exch(arr,1, N);
            N--;
            sink(arr, 1, N);
        }
    }
}
