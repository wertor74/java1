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
        // сравниваем длину массивов и приводим к большей длине
        if (digits.length > num.digits.length) {
            BigInteger valueAdd = num.toInt();
            num.digits = Arrays.copyOf(num.digits, digits.length);
            Arrays.fill(num.digits, (byte) 0);
            num.fromInt(valueAdd);
        } else if (num.digits.length > digits.length) {
            BigInteger valueAdd = num.toInt();
            digits = Arrays.copyOf(digits, num.digits.length);
            Arrays.fill(digits, (byte) 0);
            this.fromInt(valueAdd);
        }
        // складываем числа
        for (int i = digits.length; i > 0 ; i--) {
            switch (i) {
                case 1:
                    if ((byte) (digits[i - 1] + num.digits[i - 1]) > 9) {
                        Arrays.fill(digits, (byte) 0);
                        return false;
                    }
                    digits[i - 1] = (byte) (digits[i - 1] + num.digits[i - 1]);
                    break;
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
        ArrayInteger arrayInteger = new ArrayInteger(8);
        arrayInteger.fromInt(new BigInteger("18096253"));
        System.out.println(Arrays.toString(arrayInteger.digits));
        System.out.println(arrayInteger.toInt() + ", " + arrayInteger.toInt().getClass());
        ArrayInteger num = new ArrayInteger(6);
        num.fromInt(new BigInteger("170851"));
        System.out.println(Arrays.toString(num.digits));
        System.out.println(num.toInt() + ", " + num.toInt().getClass());
        System.out.println(arrayInteger.add(num) + ", " + Arrays.toString(arrayInteger.digits));
    }
}
