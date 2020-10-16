package ru.progwards.java1.lessons.bitsworld;

public class SumBits {
    public static int sumBits(byte value) {
        int x = value;
        int result = 0;
        int i = 0;
        while (i < 32) {
            int y = x & 1;
            result += y;
            x >>= 1;
            i++;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(sumBits((byte) -128));
    }
}
