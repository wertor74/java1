package ru.progwards.java1.lessons.arrays;

import java.util.Arrays;

public class ArraySort {
    public static void sort(int[] a) {
        for (int i = 0; i <= a.length-2; i++) {
            for (int j = i+1; j <= a.length-1; j++) {
                if (a[i] > a[j]) {
                    int k = a[j];
                    a[j] = a[i];
                    a[i] = k;
                }
            }
        }
    }

    public static void main(String[] args) {
        int [] a = {1, 0, -1, -2, -3};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
