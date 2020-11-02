package ru.progwards.java1.lessons.interfaces;

public class CalculateFibonacci {
    private static CacheInfo lastFibo;
    public static int fiboNumber(int n) {
        int fibo = 0;
        int fibo1 = 1;
        int fibo2 = 1;
        for (int i = 1; i <= n; i++) {
            if (i == 1) fibo = fibo1;
            else if (i == 2) fibo = fibo2;
            else {
                fibo = fibo1 + fibo2;
                fibo1 = fibo2;
                fibo2 = fibo;
            }
        }
        if (lastFibo == null) {
            lastFibo = new CacheInfo(n, fibo);
        } else if (lastFibo.n == n) {
            return getLastFibo().fibo;
        } else {
            clearLastFibo();
            lastFibo = new CacheInfo(n, fibo);
            return getLastFibo().fibo;
        }
        return fibo;
    }
    public static class CacheInfo {
        public int n;
        public int fibo;
        CacheInfo(int n, int fibo) {
            this.n = n;
            this.fibo = fibo;
        }
    }
    public static CacheInfo getLastFibo() {
        return lastFibo;
    }
    public static void clearLastFibo() {
        lastFibo = null;
    }

    public static void main(String[] args) {
        fiboNumber(6);
        fiboNumber(5);
        System.out.println(lastFibo.fibo);
    }
}