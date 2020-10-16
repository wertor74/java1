package ru.progwards.java1.lessons.bitsworld;

public class Binary {
    private int x;
    public Binary(byte num) {
        x = num;
    }
    @Override
    public String toString() {
        String result = "";
        for (int i = 1; i < 9; i++) {
            int y = x & 1;
            result = Integer.toString(y) + result;
            x >>= 1;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Binary((byte) 4).toString());
    }
}