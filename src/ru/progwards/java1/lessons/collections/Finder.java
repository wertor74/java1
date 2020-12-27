package ru.progwards.java1.lessons.collections;

import java.util.*;

public class Finder {
    public static Collection<Integer> findMinSumPair(Collection<Integer> numbers) {
        List<Integer> sum = new ArrayList(); // список сумм чисел
        List<Integer> indSumMin = new ArrayList<>(); // список индексов минимальной суммы
        Integer[] numArray = new Integer[numbers.size()]; // массив для numbers
        numbers.toArray(numArray); // передаём numbers в массив
        // вычисляем суммы и добавляем в список
        for (int i = 0; i < numArray.length - 1; i++) {
            sum.add(numArray[i] + numArray[i + 1]);
        }
        // сравниваем элемент списка сумм с минимальным элементом
        for (Integer i = 0; i < sum.size(); i++) {
            if (sum.get(i) == Collections.min(sum)) {
                indSumMin.add(i);
                indSumMin.add(i + 1);
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
        List<String> names = new ArrayList();
        names.add("Алексей");
        names.add("Алексей");
        names.add("Мария");
        names.add("Мария");
        names.add("Екатерина");
        names.add("Екатерина");
        names.add("Василий");
        names.add("Василий");
        names.add("Дмитрий");
        names.add("Дмитрий");
        Finder finder = new Finder();
        System.out.println(finder.findSimilar(names));
    }
}
