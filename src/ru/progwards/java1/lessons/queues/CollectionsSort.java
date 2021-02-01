package ru.progwards.java1.lessons.queues;

import java.util.*;

public class CollectionsSort {
    public static void mySort(Collection<Integer> data) {
        List<Integer> result = new ArrayList<>(data);
        for (int i = 0; i < result.size() - 1; i++) {
            for (int j = i + 1; j < result.size(); j++) {
                if (result.get(i) > result.get(j)) Collections.swap(result, i, j);
            }
        }
        data.clear();
        data.addAll(result);
    }
    public static void minSort(Collection<Integer> data) {
        Collection<Integer> result = new ArrayList<>();
        while (data.size() != 0) {
            result.add(Collections.min(data));
            data.remove(Collections.min(data));
        }
        data.addAll(result);
    }
    static void collSort(Collection<Integer> data) {
        List<Integer> result = new ArrayList<>(data);
        data.clear();
        Collections.sort(result);
        data.addAll(result);
    }
    public static Collection<String> compareSort() {
        Collection<String> result = new ArrayList<>(); // конечный список
        Collection<Integer> data = new ArrayList<>(); // список для проверки методов
        for (int i = 10000; i > 0 ; i--) {
            data.add(i);
        }
        // проверяем методы на производительность
        long start = System.currentTimeMillis();
        mySort(data);
        long mySort = System.currentTimeMillis() - start;
        data.clear();
        for (int i = 10000; i > 0 ; i--) {
            data.add(i);
        }
        start = System.currentTimeMillis();
        minSort(data);
        long minSort = System.currentTimeMillis() - start;
        data.clear();
        for (int i = 10000; i > 0 ; i--) {
            data.add(i);
        }
        start = System.currentTimeMillis();
        collSort(data);
        long collSort = System.currentTimeMillis() - start;
        // Локальный класс для объекта типа Метод
        class Method {
            String name;
            long perf;
            Method(String name, long perf) {
                this.name = name;
                this.perf = perf;
            }
        }
        // Создаём объекты типа Метод
        Method method_1 = new Method("mySort", mySort);
        Method method_2 = new Method("minSort", minSort);
        Method method_3 = new Method("collSort", collSort);
        // Добавляем их в список, сортируя их по производительности
        TreeSet<Method> method = new TreeSet(new Comparator<Method>() {
            @Override
            public int compare(Method o1, Method o2) {
                return Long.compare(o1.perf, o2.perf);
            }
        });
        method.addAll(List.of(method_1, method_2, method_3));
        // Добавляем имена методов в конечный список
        for (Method m : method) {
            result.add(m.name);
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> data = new ArrayList<>(List.of(1, 0, -1, -2, -3));
        System.out.println(compareSort());
    }
}
