package ru.progwards.java1.lessons.arrays;

import java.util.Arrays;

public class DIntArray {
    private int [] arr = {};
    public DIntArray() {
    }
    public void add(int num) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length-1] = num;
    }
    public void atInsert(int pos, int num) {
        arr = Arrays.copyOf(arr, arr.length+1);
        for (int i = arr.length-1; i >= pos; i--) {
            arr[i] = arr[i-1];
        }
        arr[pos] = num;
    }
    public void atDelete(int pos) {
        int [] arr1 = new int[arr.length-1];
        for (int i = 0; i < arr.length-1; i++) {
            if (i >= pos) {
                arr1[i] = arr[i+1];
            } else {
                arr1[i] = arr[i];
            }
        }
        arr = Arrays.copyOf(arr1, arr1.length);
    }
    public int at(int pos) {
        return arr[pos];
    }

    public static void main(String[] args) {
        DIntArray dia = new DIntArray();
        dia.add(-78);
        dia.add(-35);
        dia.add(-21);
        dia.add(66);
        dia.add(-79);
        dia.add(80);
        dia.add(25);
        dia.add(56);
        dia.add(-91);
        dia.atDelete(6);
        System.out.println(dia.at(6));
    }
}