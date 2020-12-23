package ru.progwards.java1.lessons.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Creator {
    public static Collection<Integer> fillEven(int n) {
        List<Integer> even = new ArrayList(n);
        for (Integer i = 1; i <= n; i++) {
            even.add(i * 2);
        }
        return even;
    }
    public static Collection<Integer> fillOdd(int n) {
        LinkedList<Integer> odd = new LinkedList();
        for (Integer i = 1; i <= n; i++) {
            odd.push((i * 2) - 1);
        }
        return odd;
    }
    public static Collection<Integer> fill3(int n) {
        List<Integer> three = new ArrayList();
        for (Integer i = 0; i < n * 3; i = i + 3) {
            three.add(i);
            three.add(i * i);
            three.add(i * i * i);
        }
        return three;
    }

    public static void main(String[] args) {
        Creator creator = new Creator();
        System.out.println(creator.fill3(10));
    }
}