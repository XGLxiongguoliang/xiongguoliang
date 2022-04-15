package com.example.network.temp.algorithm.sort;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
        Integer[] array = new Integer[]{1, 2, 5, 4, 9, 6, 66, 55, 99, 100, 3};

        System.out.println("SelectSort " + Arrays.toString(selectSort(array)));
    }

    public static Integer[] selectSort(Integer[] a) {

        for (int i = 0; i < a.length; i++) {
            // 初始本次循环最小值下标（默认外循环元素）
            int min = i;

            // 找出本轮循环最小值的下标
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }

            // 如果最小元素不是外循环元素,交换外循环元素和最小元素
            if (min != i) {
                SortUtils.exch(a, i, min);
            }
        }
        return a;
    }
}
