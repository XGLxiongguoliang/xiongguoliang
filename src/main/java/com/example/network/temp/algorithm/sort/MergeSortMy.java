package com.example.network.temp.algorithm.sort;

import java.util.Arrays;

public class MergeSortMy {
    private static int[] temp;

    public static void main(String[] args) {
        int[] a = new int[]{1,5,3,7,44,9,2,10,100};
        sort(a);
        System.out.println(Arrays.toString(a));
    }

    public static void sort(int[] a) {
        temp = new int[a.length];
        divisionAndMerge(a, 0, a.length - 1);
    }

    public static void divisionAndMerge(int[] a, int left, int right) {
        // 如果左边下标大于或者等于右边下标，说明只有唯一元素，不需要分割
        if (left >= right) {
            return;
        }

        int mid = left + (right -left) / 2;

        // 递归分割左边
        divisionAndMerge(a, left, mid);
        // 左边分割结束后，递归分割右边
        divisionAndMerge(a, mid + 1, right);
        // 最后合并
        merge(a, left, mid, right);
    }

    public static void merge(int[] a, int left, int mid, int right) {
        // i-左边数组第一个元素下标
        int i = left;
        // j-右边数组第一个小标
        int j = mid +1;

        // 将left到right下标对应的数据存入temp中
        for (int k = left; k <= right; k++) {
            temp[k] = a[k];
        }

        // 将temp中数据归并到a中
        for (int m = left; m <= right; m++) {
            if (i > mid) {
                a[m] = temp[j++];
            } else if (j > right) {
                a[m] = temp[i++];
            } else if (SortUtils.less(temp[j], temp[i])) {
                a[m] = temp[j++];
            } else {
                a[m] = temp[i++];
            }
        }
    }
}
