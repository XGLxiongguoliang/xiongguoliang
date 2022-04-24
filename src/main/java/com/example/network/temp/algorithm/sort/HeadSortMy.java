package com.example.network.temp.algorithm.sort;

import java.util.Arrays;

/**
 * @program com.example.network.temp.algorithm.sort
 * @description 自己写的堆排序
 * @auther Mr.Xiong
 * @create 2022-04-17 11:31:09
 */
public class HeadSortMy {

    public static void main(String[] args) {
        Integer[] a = new Integer[]{1, 2, 5, 4, 9, 6, 66, 55, 99, 100, 3};
        System.out.println("headSort " + Arrays.toString(headSort(a)));
    }

    public static Integer[] headSort(Integer[] a) {
        // 创建大顶堆
        createHeap(a);

        // 交换堆顶和堆尾的数据,重置大顶堆
        for (int i = 1; i < a.length; i++) {
            SortUtils.exch(a, 0, a.length - i);
            adjustHeap(a, 0, a.length - i);
        }

        return a;
    }

    public static void createHeap(Integer[] a) {
        // 循环调整堆，最终得到一个大顶堆
        for (int i = a.length / 2 - 1; i >= 0; i--) {
            adjustHeap(a, i, a.length);
        }
    }

    public static void adjustHeap(Integer[] a, int i, int len) {
        // 父节点和其下的左右节点中，最大值的节点下标[默认父节点值最大]
        int maxIndex = i;
        // 左节点下标
        int leftIndex = i*2 + 1;
        // 右节点下标
        int rightIndex = i*2 + 2;

        // 比较三个数，找出最大值对应的下标
        if (leftIndex < len && a[leftIndex] > a[maxIndex]) {
            maxIndex = leftIndex;
        }
        if (rightIndex < len && a[rightIndex] > a[maxIndex]) {
            maxIndex = rightIndex;
        }

        // 如果父节点比左右叶子节点某个值小的话，则交换值，并重新调整大顶堆
        if (maxIndex != i) {
            SortUtils.exch(a, i, maxIndex);
            adjustHeap(a, maxIndex, len);
        }
    }
}
