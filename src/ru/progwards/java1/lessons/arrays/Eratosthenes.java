package ru.progwards.java1.lessons.arrays;

import java.util.Arrays;

public class Eratosthenes {
    private boolean[] sieve;
    public Eratosthenes(int N) {
        this.sieve = new boolean[N];
        Arrays.fill(sieve, true);
        sieve[0] = false;
        sift();
    }
    private void sift() {
        for (int i = 2; i < sieve.length-1; i++) {
            for (int j = 2; j <= (sieve.length / i); j++) {
                sieve[i*j-1] = false;
            }
        }
    }
    public boolean isSimple(int n) {
        return sieve[n - 1];
    }

    public static void main(String[] args) {
        System.out.println(new Eratosthenes(13).isSimple(13));
    }
}
