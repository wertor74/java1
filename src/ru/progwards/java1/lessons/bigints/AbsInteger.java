package ru.progwards.java1.lessons.bigints;

import java.math.BigInteger;

public class AbsInteger {
    @Override
    public String toString() {
        return "Абстрактное целое число";
    }
    static AbsInteger add(AbsInteger num1, AbsInteger num2) {
        // приводим к BigInteger
        BigInteger num3 = num1.toBigInteger();
        BigInteger num4 = num2.toBigInteger();
        // вычисляем сумму
        BigInteger sumBI = num3.add(num4);
        return toAbsInteger(sumBI);
    }
    public BigInteger toBigInteger() {
        return new BigInteger(toString());
    }
    // приводим сумму к нужному виду AbsInteger
    static AbsInteger toAbsInteger(BigInteger sumBI) {
        // приводим сумму к строке
        String sumBIStr = String.valueOf(sumBI);
        // переменные для определения типа суммы
        // границы byte
        BigInteger biByteLow = new BigInteger(String.valueOf(Byte.MIN_VALUE));
        BigInteger biByteHigh = new BigInteger(String.valueOf(Byte.MAX_VALUE));
        // границы short
        BigInteger biShortLow = new BigInteger(String.valueOf(Short.MIN_VALUE));
        BigInteger biShortHigh = new BigInteger(String.valueOf(Short.MAX_VALUE));
        // проверяем диапазон
        if (sumBI.compareTo(biByteLow) >= 0 && sumBI.compareTo(biByteHigh) <= 0) {
            AbsInteger sum = new ByteInteger(Byte.valueOf(sumBIStr));
            return sum;
        } else if ((sumBI.compareTo(biShortLow) >= 0 && sumBI.compareTo(biByteLow) < 0)
                || (sumBI.compareTo(biShortHigh) <= 0) && sumBI.compareTo(biByteHigh) > 0) {
                    AbsInteger sum = new ShortInteger(Short.valueOf(sumBIStr));
                    return sum;
        } else {
            AbsInteger sum = new IntInteger(Integer.valueOf(sumBIStr));
            return sum;
        }
    }

    public static void main(String[] args) {
        AbsInteger num1 = new ByteInteger((byte) 2);
        AbsInteger num2 = new IntInteger(-32768);
        System.out.println("Сумма = " + add(num1, num2) + "; Тип суммы: " + add(num1, num2).getClass());
    }
}
