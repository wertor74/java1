package ru.progwards.java1.lessons.compare_if_cycles;

public class TriangleInfo {
    public static boolean isTriangle(int a, int b, int c) {
        if (a + b > c && b + c > a && a + c > b) return true;
    return false;
    }
    public static boolean isRightTriangle(int a, int b, int c) {
        if (a > b && a > c) {
            if (a * a == b * b + c * c) return true;
        } else if (b > a && b > c) {
            if (b * b == a * a + c * c) return true;
        } else {
            if (c * c == a * a + b * b) return true;
        }
    return false;
    }
    public static boolean isIsoscelesTriangle(int a, int b, int c) {
        if (a + b > c && a == b || b == c || a == c) return true;
    return false;
    }
    public static void main(String[] args) {
        System.out.println(isTriangle(301, 100,201));
    }
}