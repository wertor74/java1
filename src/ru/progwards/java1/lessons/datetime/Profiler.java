package ru.progwards.java1.lessons.datetime;

import java.util.*;

public class Profiler {
    public static HashMap <String, Integer> sectionCount = new HashMap<>(); // словарь для количества вызовов
    public static HashMap <String, Long> sectionStart = new HashMap<>(); // словарь для времени старта
    public static HashMap <String, Long> sectionFinish = new HashMap<>(); // словарь для времени финиша
    public static HashMap <String, Integer> sectionFullTime = new HashMap<>(); // словарь для фултайм
    public static HashMap <String, Integer> sectionSelfTime = new HashMap<>(); // словарь для селфтайм
    public static void enterSection(String name) {
        long start = System.currentTimeMillis();
        if (!Profiler.sectionCount.containsKey(name)) {
            Profiler.sectionCount.put(name, 1);
        } else {
            Profiler.sectionCount.put(name, Profiler.sectionCount.get(name) + 1);
        }
        Profiler.sectionStart.put(name, start);
    }
    public static void exitSection(String name) {
        long finish = System.currentTimeMillis();
        long sectionTime = finish - Profiler.sectionStart.get(name);
        Profiler.sectionFinish.put(name, finish);
        Profiler.sectionFullTime.put(name, (int) sectionTime);
    }
    public static List<StatisticInfo> getStatisticInfo() {
        List<StatisticInfo> statInfo = new ArrayList<>();
        // массив для имён секций
        String [] sftArr = Profiler.sectionFullTime.keySet().toArray(new String[Profiler.sectionFullTime.size()]);
        // вычисляем селфтайм
        if (sftArr.length == 1) Profiler.sectionSelfTime.put(sftArr[0], sectionFullTime.get(sftArr[0]) * sectionCount.get(sftArr[0]));
        for (int i = 1; i < sftArr.length; i++) {
            if (i == 1) {
                Profiler.sectionSelfTime.put(sftArr[i - 1], Profiler.sectionFullTime.get(sftArr[i - 1]) - Profiler.sectionFullTime.get(sftArr[i]) * Profiler.sectionCount.get(sftArr[i]));
            } else {
                if (Profiler.sectionFinish.get(sftArr[i]) > Profiler.sectionStart.get(sftArr[i - 1]) && Profiler.sectionFinish.get(sftArr[i]) < Profiler.sectionFinish.get(sftArr[i - 1])) {
                    Profiler.sectionSelfTime.put(sftArr[i - 1], (Profiler.sectionFullTime.get(sftArr[i - 1]) * Profiler.sectionCount.get(sftArr[i - 1])) - Profiler.sectionFullTime.get(sftArr[i]));
                    if (i == sftArr.length - 1) Profiler.sectionSelfTime.put(sftArr[i], Profiler.sectionFullTime.get(sftArr[i]) * Profiler.sectionCount.get(sftArr[i]));
                } else {
                    Profiler.sectionSelfTime.put(sftArr[0], Profiler.sectionSelfTime.get(sftArr[0]) - Profiler.sectionFullTime.get(sftArr[i]));
                    Profiler.sectionSelfTime.put(sftArr[i - 1], Profiler.sectionFullTime.get(sftArr[i - 1]) * Profiler.sectionCount.get(sftArr[i - 1]));
                    if (i == sftArr.length - 1) Profiler.sectionSelfTime.put(sftArr[i], Profiler.sectionFullTime.get(sftArr[i]) * Profiler.sectionCount.get(sftArr[i]));
                }
            }
        }
        // заносим статистику в ArrayList
        for (int i = 0; i < sftArr.length; i++) {
            StatisticInfo statisticInfo = new StatisticInfo();
            statisticInfo.sectionName = sftArr[i];
            statisticInfo.fullTime = Profiler.sectionFullTime.get(sftArr[i]) * Profiler.sectionCount.get(sftArr[i]);
            statisticInfo.selfTime = Profiler.sectionSelfTime.get(sftArr[i]);
            statisticInfo.count = Profiler.sectionCount.get(sftArr[i]);
            statInfo.add(statisticInfo);
        }
        return statInfo;
    }
    public static void main(String[] args) {
        for (int i = 1; i < 4; i++) {
            Profiler.enterSection("1");
            long startTest = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTest < 100) {

            }
            Profiler.exitSection("1");
        }
        System.out.println(Profiler.sectionCount);
        System.out.println(Profiler.sectionStart);
        System.out.println(Profiler.sectionFinish);
        System.out.println(Profiler.sectionFullTime);
        System.out.println(Profiler.getStatisticInfo());
        System.out.println(Profiler.sectionSelfTime);
    }
}