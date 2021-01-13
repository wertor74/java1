package ru.progwards.java1.lessons.sets;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SetOperations {
    public static Set<Integer> union(Set<Integer> set1, Set<Integer> set2) {
        HashSet result = new HashSet(set1);
        result.addAll(set2);
        return result;
    }
    public static Set<Integer> intersection(Set<Integer> set1, Set<Integer> set2) {
        HashSet result = new HashSet(set1);
        result.retainAll(set2);
        return result;
    }
    public static Set<Integer> difference(Set<Integer> set1, Set<Integer> set2) {
        HashSet result = new HashSet(set1);
        result.removeAll(set2);
        return result;
    }
    public static Set<Integer> symDifference(Set<Integer> set1, Set<Integer> set2) {
        HashSet result = new HashSet(set1);
        HashSet result1 = new HashSet(set2);
        result.removeAll(set2);
        result1.removeAll(set1);
        result.addAll(result1);
        return result;
    }

    public static void main(String[] args) {
        Set<Integer> set1 = Set.of(5, 4, 3, 2, 1);
        Set<Integer> set2 = Set.of(6, 7, 8, 9, 10, 4, 1, 5);
        System.out.println(symDifference(set1, set2));
    }
}
