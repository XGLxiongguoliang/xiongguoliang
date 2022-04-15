package com.example.network.temp.algorithm.sort;

import java.util.Arrays;

/**
 * 插入排序
 */
public class InsertSort {
    public static void main(String[] args) {
        Integer[] array = new Integer[]{1, 2, 5, 4, 9, 6, 66, 55, 99, 100, 3};

        System.out.println("insertSort " + Arrays.toString(insertSort(array)));
    }

    public static Integer[] insertSort(Integer[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (SortUtils.less(a[j], a[j - 1])) {
                    SortUtils.exch(a, j, j - 1);
                }
            }
        }
        return a;
    }
}
