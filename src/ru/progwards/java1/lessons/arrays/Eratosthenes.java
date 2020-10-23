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
        for (int p = 2; p <= sieve.length / p;) {
            for (int i = 2; i <= (sieve.length / p); i++) {
                sieve[p * i - 1] = false;
            }
            for (int k = p; k < sieve.length; k++) {
                if (sieve[k] == true) {
                    p = k + 1;
                    break;
                }
            }
        }
    }
    public boolean isSimple(int n) {
        return sieve[n - 1];
    }

    public static void main(String[] args) {
        new Eratosthenes(20);
    }
}
