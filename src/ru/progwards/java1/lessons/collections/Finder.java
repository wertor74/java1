package ru.progwards.java1.lessons.collections;

import java.util.*;

public class Finder {
    public static Collection<Integer> findMinSumPair(Collection<Integer> numbers) {
        List<Integer> indSumMin = new ArrayList<>(2); // список индексов минимальной суммы
        Integer[] numArray = new Integer[numbers.size()]; // массив для numbers
        numbers.toArray(numArray); // передаём numbers в массив
        Integer sumMin = numArray[0] + numArray[1];
        indSumMin.add(0);
        indSumMin.add(1);
        // вычисляем суммы и добавляем в список
        for (Integer i = 1; i < numArray.length - 1; i++) {
            Integer sum = numArray[i] + numArray[i + 1];
            if (sum < sumMin) {
                sumMin = sum;
                indSumMin.set(0, i);
                indSumMin.set(1, i + 1);
            }
        }
        return indSumMin;
    }
    public static Collection<Integer> findLocalMax(Collection<Integer> numbers) {
        List<Integer> localMax = new ArrayList<>(); // список для локальных максимумов
        Integer[] numberArray = new Integer[numbers.size()]; // массив для numbers
        numbers.toArray(numberArray); // передаём numbers в массив
        // ищем локальные максимумы и передаём в список
        for (int i = 1; i < numberArray.length - 1; i++) {
            if (numberArray[i] > numberArray[i -1] && numberArray[i] > numberArray[i + 1]) {
                localMax.add(numberArray[i]);
            }
        }
        return localMax;
    }
    public static boolean findSequence(Collection<Integer> numbers) {
        List<Integer> digits = new ArrayList();
        for (Integer i = 1; i <= numbers.size(); i++) {
            digits.add(i);
        }
        return numbers.containsAll(digits);
    }
    public static String findSimilar(Collection<String> names) {
        String result = "";
        String[] namesArray = new String[names.size()]; // массив для names
        names.toArray(namesArray); // передаём names в массив
        List<Integer> replay = new ArrayList(); // список для количества повторов
        // заполняем список нолями
        for (int i = 0; i < names.size(); i++) {
            replay.add(i, 0);
        }
        // сравниваем имена и считаем повторы
        for (int i = 0; i < namesArray.length - 1; i++) {
            Integer count = 1; // счётчик повторов
            while (i < namesArray.length - 1 && namesArray[i].compareTo(namesArray[i + 1]) == 0) {
                i ++;
                count ++;
            }
            replay.set(i, count); // вносим количество повторов в список
        }
        // выводим результат: элемент из массива имён по индексу максимума списка повторов
        // + максимум списка повторов
        result = namesArray[replay.indexOf(Collections.max(replay))] + ":" + Collections.max(replay);
        return result;
    }

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList();
        numbers.add(4);
        numbers.add(5);
        numbers.add(2);
        numbers.add(3);
        numbers.add(0);
        numbers.add(1);
        numbers.add(6);
        numbers.add(7);
        numbers.add(8);
        numbers.add(9);
        System.out.println(findMinSumPair(numbers));
    }
}
