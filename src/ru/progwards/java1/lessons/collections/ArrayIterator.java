package ru.progwards.java1.lessons.collections;

import java.util.Iterator;

public class ArrayIterator<T> implements Iterator<T> {
    private T[] array;
    int count;

    ArrayIterator(T[] array) {
        this.array = array;
        count = -1;
    }
    @Override
    public boolean hasNext() {
        // TODO Auto-generated method stub
        if (count + 1 < array.length) return true;
        return false;
    }
    @Override
    public T next() {
        // TODO Auto-generated method stub
        count++;
        return array[count];
    }

    public static void main(String[] args) {
        String[] array = {"один", "два", "три", "четыре", "пять"};
        ArrayIterator arrayIterator = new ArrayIterator(array);
        for (int i = 0; i < arrayIterator.array.length; i++) {
            System.out.println("array[" + i + "] = " + arrayIterator.hasNext());
            System.out.println("array[" + i + "] = " + arrayIterator.next());
        }
        while (arrayIterator.hasNext()) {
            System.out.println(arrayIterator.next());
        }
    }
}