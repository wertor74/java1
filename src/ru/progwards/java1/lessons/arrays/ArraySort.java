package ru.progwards.java1.lessons.arrays;

import java.util.Arrays;

public class ArraySort {
    public static void sort(int[] a) {
        int[] b = Arrays.copyOf(a, a.length);
        for (int i = 0; i <= a.length-2; i++) {
            for (int j = i+1; j <= a.length-1; j++) {
                if (a[i] > a[j]) {
                    a[i] = b[j];
                    a[j] = b[i];
                    b = Arrays.copyOf(a, a.length);
                }
            }
        }
    }

    public static void main(String[] args) {
        int [] a = {5, 4, 3, 2, 1};
        sort(a);
    }
}
