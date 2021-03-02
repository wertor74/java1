package ru.progwards.java1.lessons.maps;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;

public class FiboMapCache {
    private static Map<Integer, BigDecimal> fiboCache;
    public FiboMapCache(boolean cacheOn) {
        if (cacheOn) fiboCache = new HashMap<>();
    }
    public static BigDecimal fiboNumber(int n) {
        if (fiboCache != null && fiboCache.containsKey(n)) {
            return fiboCache.get(n);
        } else {
            BigDecimal fibo = ZERO;
            BigDecimal fibo1 = ONE;
            BigDecimal fibo2 = ONE;
            for (int i = 1; i <= n; i++) {
                if (i == 1) fibo = fibo1;
                else if (i == 2) fibo = fibo2;
                else {
                    fibo = fibo1.add(fibo2);
                    fibo1 = fibo2;
                    fibo2 = fibo;
                }
            }
            if (fiboCache != null) fiboCache.put(n, fibo);
            return fibo;
        }
    }
    public static void clearCahe() {
        fiboCache = null;
    }
    public static void test() {
        // тест для cacheOn = true
        FiboMapCache cacheTrue = new FiboMapCache(true);
        // сначала заполняем кэш
        for (int i = 1; i <= 1000; i++) cacheTrue.fiboNumber(i);
        long start = System.currentTimeMillis();
        for (int i = 1; i <= 1000; i++) {
            cacheTrue.fiboNumber(i);
        }
        long finish = System.currentTimeMillis() - start;
        System.out.println("fiboNumber cacheOn = true, время выполнения " +  finish + " мсек");
        // тест для cacheOn = false
        clearCahe();
        FiboMapCache cacheFalse = new FiboMapCache(false);
        start = System.currentTimeMillis();
        for (int i = 1; i <= 1000; i++) {
            cacheFalse.fiboNumber(i);
        }
        finish = System.currentTimeMillis() - start;
        System.out.println("fiboNumber cacheOn = false, время выполнения " +  finish + " мсек");

    }
}
