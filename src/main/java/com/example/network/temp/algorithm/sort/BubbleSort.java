package com.example.network.temp.algorithm.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        Integer[] array = new Integer[]{1, 2, 5, 4, 9, 6, 66, 55, 99, 100, 3};

        System.out.println("asc" + Arrays.toString(ascSort(array)));
        System.out.println("desc" + Arrays.toString(descSort(array)));
    }

    public static Integer[] ascSort(Integer[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    SortUtils.exch(array, i, j);
                }
            }
        }
        return array;
    }

    public static Integer[] descSort(Integer[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] < array[j]) {
                    SortUtils.exch(array, i, j);
                }
            }
        }
        return array;
    }
}
