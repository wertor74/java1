package ru.progwards.java1.lessons.bigints;

import java.math.BigInteger;
import java.util.Arrays;

public class ArrayInteger {
    byte[] digits;
    public ArrayInteger(int n) {
        this.digits = new byte[n];
    }
    public void fromInt(BigInteger value) {
        while (value.compareTo(BigInteger.TEN.pow(digits.length)) >= 0) {
            value = value.divide(BigInteger.TEN);
        }
        for (int i = digits.length; i > 0; i--) {
            if (i > 1) {
                digits[i - 1] = Byte.valueOf(String.valueOf(value.remainder(BigInteger.TEN)));
                value = value.divide(BigInteger.TEN);
            } else {
                digits[i - 1] = Byte.valueOf(String.valueOf(value));
            }
        }
    }
    public BigInteger toInt() {
        String strToInt = "";
        for (byte value : digits) {
            strToInt = strToInt + value;
        }
        return new BigInteger(strToInt);
    }
    boolean add(ArrayInteger num) {
        for (int i = digits.length; i > 0 ; i--) {
            switch (i) {
                case 1:
                    if ((byte) (digits[i - 1] + num.digits[i - 1]) > 9) {
                        Arrays.fill(digits, (byte) 0);
                        return false;
                    }
                default:
                    digits[i - 1] = (byte) (digits[i - 1] + num.digits[i - 1]);
                    if (digits[i - 1] > 9) {
                        digits[i - 1] = (byte) (digits[i - 1] - 10);
                        digits[i - 2] = (byte) (digits[i - 2] + 1);
                    }
            }
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
