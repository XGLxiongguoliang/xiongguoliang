package com.example.network.temp.algorithm.sort;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {
    public static void main(String[] args) {
        Integer[] a = new Integer[]{1,5,2,47,45,24,6,99,15};
        sort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }

    public static void sort(Integer[] a, int low, int high) {
        if (low >= high) {
            return;
        }

        int lt = low;
        int i = low + 1;
        int gt = high;

        int v = a[low];

        while (i <= gt) {
            if (a[i] <= v) {
                SortUtils.exch(a, lt++, i++);
            } else if (a[i] > v) {
                SortUtils.exch(a, i, gt--);
            } else {
                i++;
            }
        }
        sort(a, low, lt - 1);
        sort(a, gt + 1, high);
    }
}
