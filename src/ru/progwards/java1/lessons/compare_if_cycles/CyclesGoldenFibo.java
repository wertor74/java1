package ru.progwards.java1.lessons.compare_if_cycles;

public class CyclesGoldenFibo {
    public static boolean containsDigit(int number, int digit) {
        if (number == 0 && digit == 0) return true;
        while (number != 0) {
            if (number % 10 == digit) return true;
            number /= 10;
        }
    return false;
    }
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
    return fibo;
    }
    public static boolean isGoldenTriangle(int a, int b, int c) {
        double d1 = 1.61703;
        double d2 = 1.61903;
        double d3 = 0.0;
        if (a + b > c && a == b || b == c || a == c) {
            if (a == b) {
                d3 = (double)a / (double)c;
                if (d3 >= d1 && d3 <= d2) return true;
            } else if (b == c) {
                d3 = (double)b / (double)a;
                if (d3 >= d1 && d3 <= d2) return true;
            } else {
                d3 = (double)a / (double)b;
                if (d3 >= d1 && d3 <= d2) return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int i = 1;
        while (i <= 15) {
            System.out.print(fiboNumber(i));
            if (i == 15) System.out.println(".");
            else System.out.print(", ");
            i++;
        }
        for (int a = 1; a < 100; a++) {
            for (int c = 1; c < 100; c++) {
                if (isGoldenTriangle(a, c, c) == true) {
                    System.out.print("Длина основания = " + a + ", длина рёбер = ");
                    System.out.println(c);
                }
            }
        }
    }
}