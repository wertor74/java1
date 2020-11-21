package ru.progwards.java1.lessons.bigints;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

public class BigAlgebra {
    static BigDecimal fastPow(BigDecimal num, int pow) {
        // алгоритм слева направо
        int pow1 = pow;
        int [] powBinaryReversed = {};// массив для бинарного представления pow (развёрнутый)
        // переводим pow в бинарное представление
        for (int i = 0; ;i++) {
            powBinaryReversed = Arrays.copyOf(powBinaryReversed, powBinaryReversed.length + 1);
            powBinaryReversed[i] = pow1 % 2;
            pow1 /= 2;
            if (pow1 == 0) break;
        }
        // разворачиваем массив
        int [] powBinary = new int[powBinaryReversed.length];
        for (int i = 0; i < powBinaryReversed.length; i++) {
            powBinary[powBinaryReversed.length - 1 - i] = powBinaryReversed[i];
        }
        // применяем алгоритм
        BigDecimal result = new BigDecimal("1");
        for (int i = 0; i < powBinary.length; i++) {
            if (i == powBinary.length - 1) {
                result = (num.pow(powBinary[i])).multiply(result);
            } else {
                result = ((num.pow(powBinary[i])).multiply(result)).pow(2);
            }
        }
        return result;
    }
    BigInteger fibonacci(int n) {
        BigInteger fibo = BigInteger.ZERO;
        BigInteger fibo1 = BigInteger.ONE;
        BigInteger fibo2 = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            if (i == 1) fibo = fibo1;
            else if (i == 2) fibo = fibo2;
            else {
                fibo = fibo1.add(fibo2);
                fibo1 = fibo2;
                fibo2 = fibo;
            }
        }
        return fibo;
    }

    public static void main(String[] args) {
        System.out.println(fastPow(new BigDecimal("15"), 15));
        System.out.println(new BigDecimal("15").pow(15));
    }
}
