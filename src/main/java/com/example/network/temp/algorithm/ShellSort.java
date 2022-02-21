package com.example.network.temp.algorithm;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        int[] array = new int[]{1, 6, 7, 3, 2, 77, 8, 64, 54, 44, 22};

        int h = 1;
        while (h < array.length / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < array.length; i++) {
                for (int j = i; j >= h && array[j] < array[j - h]; j -= h) {
                    int temp = array[j];
                    array[j] = array[j - h];
                    array[j - h] = temp;
                }
            }
            h = h / 3;
        }

        System.out.println(Arrays.toString(array));
    }
}
