package com.example.network.temp.algorithm.sort;

import java.util.Arrays;

/**
 * 希尔排序
 */
public class ShellSort {
    public static void main(String[] args) {
        Integer[] array = new Integer[]{1, 6, 7, 3, 2, 77, 8, 64, 54, 44};

        shellSort(array);

        System.out.println(Arrays.toString(array));
    }

    public static Integer[] shellSort(Integer[] array) {
        // 默认增量值为1
        int h = 1;

        // 通过数组长度计算出最大增量值
        while (h < array.length / 2) {
            h = 2 * h + 1;
        }

        while (h >= 1) {
            // 将数组变成h有序
            for (int i = h; i < array.length; i++) {
                // 将a[i]插入到a[i - h],a[i-2*h],a[i-3*H]...中
                for (int j = i; j >= h; j -= h) {
                    if (array[j] < array[j - h]) {
                        SortUtils.exch(array, j, j-h);
                        System.out.println("排序变更-" + Arrays.toString(array));
                    }
                }

                System.out.println("h---" + h + "---" + Arrays.toString(array));

            }

            h = h / 2;
        }

        return array;
    }
}
