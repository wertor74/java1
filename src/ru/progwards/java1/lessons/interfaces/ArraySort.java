package ru.progwards.java1.lessons.interfaces;

public class ArraySort implements CompareWeight {
    public static void sort(CompareWeight[] a) {
        for (int i = 0; i <= a.length-2; i++) {
            for (int j = i+1; j <= a.length-1; j++) {
                if (a[i].compareWeight(a[j]) == CompareWeight.CompareResult.GREATER) {
                    CompareWeight b = a[j];
                    a[j] = a[i];
                    a[i] = b;
                }
            }
        }
    }

    @Override
    public CompareResult compareWeight(CompareWeight smthHasWeigt) {
        return null;
    }
}
