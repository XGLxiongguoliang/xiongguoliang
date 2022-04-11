package com.example.network.temp.algorithm.find;

import java.util.Arrays;

/**
 * @program com.example.network.temp.algorithm.sort
 * @description 二分查找
 * @auther Mr.Xiong
 * @create 2022-04-10 11:38:38
 */
public class BirnaryFind {
    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5, 7, 8, 43, 54, 99, 100};

        int result = birnaryFind(array, 8);

        System.out.println("目标查找值的数组下标 --- " + result);
    }

    public static int birnaryFind(int[] array, int target) {
        int leftIndex = 0;
        int rightIndex = array.length - 1;
        int middle = leftIndex + (rightIndex - leftIndex) / 2;

        while (array[middle] != target) {
            if (target > array[middle]) {
                leftIndex = middle + 1;
            } else {
                rightIndex = middle - 1;
            }

            if (leftIndex > rightIndex) {
                return -1;
            }

            middle = leftIndex + (rightIndex - leftIndex) / 2;
        }

        return middle;
    }
}
