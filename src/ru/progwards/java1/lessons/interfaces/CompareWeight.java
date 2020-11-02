package ru.progwards.java1.lessons.interfaces;

public interface CompareWeight {
    public enum CompareResult {
        LESS,
        EQUAL,
        GREATER
    }
    public CompareResult compareWeight(CompareWeight smthHasWeigt);
    public static void sort(CompareWeight[] a) {
        for (int i = 0; i <= a.length-2; i++) {
            for (int j = i+1; j <= a.length-1; j++) {
                if (a[i].compareWeight(a[j]) == CompareResult.GREATER) {
                    Animal b = (Animal) a[j];
                    a[j] = a[i];
                    a[i] = b;
                }
            }
        }
    }
}