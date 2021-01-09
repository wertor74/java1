package ru.progwards.java1.lessons.collections;

import java.util.Iterator;

public class MatrixIterator<T> implements Iterator {
    private T[][] array;
    int count1, count2;
    MatrixIterator(T[][] array) {
        this.array = array;
        count1 = 0;
        count2 = -1;
    }
    @Override
    public boolean hasNext() {
        if (count2 + 1 < array[count1].length) {
            return true;
        } else if (count1 < array.length - 1) {
            count1++;
            count2 = 0;
            return true;
        }
        return false;
    }
    @Override
    public T next() {
        if (count2 + 1 < array[count1].length) {
            count2++;
            return array[count1][count2];
        } else if (count1 < array.length - 1) {
            count1++;
            count2 = 0;
            return array[count1][count2];
        }
        return null;
    }
    public static void main(String[] args) {
        String[] a = {"00", "01", "02", "03", "04"};
        String[] a1 = {"10", "11", "12", "13", "14"};
        String[] a2 = {"20", "21", "22", "23", "24"};
        String[] a3 = {"30", "31", "32", "33", "34"};
        String[] a4 = {"40", "41", "42", "43", "44"};
        String[][] array = {a, a1, a2, a3, a4};
        MatrixIterator matrixIterator = new MatrixIterator(array);
        while (matrixIterator.hasNext()) {
            System.out.print(matrixIterator.next() + ", ");
        }
    }
}
