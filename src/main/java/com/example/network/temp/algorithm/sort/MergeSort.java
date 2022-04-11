package com.example.network.temp.algorithm.sort;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {
    // 归并所需的辅助数组
    private static int[] aux;

    public static void sort(int[] a) {
        // 一次性分配空间
        aux = new int[a.length];
        sort(a, 0, a.length - 1);
    }

    private static void sort(int[] a, int lo, int hi) {
        // 将数组a[lo..hi]排序
        if (hi <= lo) return;
        int mid = lo + (hi - lo)/2;
        // 将左半边排序
        sort(a, lo, mid);
        // 将右半边排序
        sort(a, mid+1, hi);
        // 归并结果
        merge(a, lo, mid, hi);
    }

    public static void merge(int[] a, int lo, int mid, int hi) {
        // 将a[lo..mid] 和 a[mid+1..hi] 归并
        int i = lo, j = mid+1;
        // 将a[lo..hi]复制到aux[lo..hi]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        // 归并回到a[lo..hi]
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            }
            else if (j > hi ) {
                a[k] = aux[i++];
            }
            else if (SortUtils.less(aux[j], aux[i])) {
                a[k] = aux[j++];
            }
            else {
                a[k] = aux[i++];
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 5, 4, 9, 6, 66, 55, 99, 100, 3};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
