package ru.progwards.java1.lessons.bitsworld;

public class CheckBit {
    public static int checkBit(byte value, int bitNumber) {
        int x = value;
        x >>= bitNumber;
        int result = x & 1;
        return result;
    }

    public static void main(String[] args) {
        System.out.println(checkBit((byte) -45, 3));
    }
}
