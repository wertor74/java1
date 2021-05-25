package ru.progwards.java1.lessons.datetime;

import java.util.*;

public class Profiler {
    public static Map <String, Integer> sectionCount = new TreeMap<>(); // словарь для количества вызовов
    public static Map <String, Long> sectionStartFull = new TreeMap<>(); // словарь для времени старта фулл
    public static Map <String, Long> sectionStartSelf = new TreeMap<>(); // словарь для времени старта селф
    public static Map <String, Long> sectionFinish = new TreeMap<>(); // словарь для времени финиша
    public static Map <String, Integer> sectionFullTime = new TreeMap<>(); // словарь для фуллтайм
    public static Map <String, Integer> sectionSelfTime = new TreeMap<>(); // словарь для селфтайм
    public static void enterSection(String name) {
        long start = System.currentTimeMillis();
        if (!Profiler.sectionCount.containsKey(name)) {
            Profiler.sectionCount.put(name, 1);
            Profiler.sectionStartFull.put(name, start);
        } else {
            Profiler.sectionCount.put(name, Profiler.sectionCount.get(name) + 1);
        }
        Profiler.sectionStartSelf.put(name, start);
    }
    public static void exitSection(String name) {
        long finish = System.currentTimeMillis();
        long sectionTime = finish - Profiler.sectionStartFull.get(name);
        Profiler.sectionFinish.put(name, finish);
        Profiler.sectionFullTime.put(name, (int) sectionTime);
    }
    public static List<StatisticInfo> getStatisticInfo() {
        List<StatisticInfo> statInfo = new ArrayList<>();
        // массив для имён секций
        String [] sftArr = Profiler.sectionFullTime.keySet().toArray(new String[Profiler.sectionFullTime.size()]);
        // вычисляем селфтайм
        if (sftArr.length == 1) {
            Profiler.sectionSelfTime.put(sftArr[0], sectionFullTime.get(sftArr[0]));
        } else {
            // вычисляем фуллтайм и селфтайм для последней секции
            sectionFullTime.put(sftArr[sftArr.length - 1], (int) (sectionFinish.get(sftArr[sftArr.length - 1]) - sectionStartSelf.get(sftArr[sftArr.length - 1])) * sectionCount.get(sftArr[sftArr.length - 1]));
            sectionSelfTime.put(sftArr[sftArr.length - 1], sectionFullTime.get(sftArr[sftArr.length - 1]));
            for (int i = 1; i < sftArr.length; i++) {
                Profiler.sectionSelfTime.put(sftArr[i - 1], Profiler.sectionFullTime.get(sftArr[i - 1]) - Profiler.sectionFullTime.get(sftArr[i]));
            }
        }
        // заносим статистику в ArrayList
        for (int i = 0; i < sftArr.length; i++) {
            StatisticInfo statisticInfo = new StatisticInfo();
            statisticInfo.sectionName = sftArr[i];
            statisticInfo.fullTime = Profiler.sectionFullTime.get(sftArr[i]);
            statisticInfo.selfTime = Profiler.sectionSelfTime.get(sftArr[i]);
            statisticInfo.count = Profiler.sectionCount.get(sftArr[i]);
            statInfo.add(statisticInfo);
        }
        return statInfo;
    }
    public static void main(String[] args) {
            Profiler.enterSection("Process1");
            long startTest = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTest < 100) {

            }
            Profiler.exitSection("Process1");
        System.out.println(Profiler.getStatisticInfo());
    }
}