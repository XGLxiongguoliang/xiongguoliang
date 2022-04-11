package com.example.network.temp.algorithm.sort;

/**
 * @program com.example.network.temp.algorithm.sort
 * @description 排序公共方法
 * @auther Mr.Xiong
 * @create 2022-04-10 16:14:36
 */
public class SortUtils {
    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i]; a[i] = a[j]; a[j] = t;
    }

    public static void show(Comparable[] a)
    {
        // 在单行中打印数组
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i] + " ");
        }
    }

    public static boolean isSorted(Comparable[] a)
    {
        // 测试数组元素是否有序
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i-1])) {
                return false;
            }
        }
        return true;
    }
}
