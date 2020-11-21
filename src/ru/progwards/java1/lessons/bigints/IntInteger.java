package ru.progwards.java1.lessons.bigints;

import java.math.BigInteger;

public class IntInteger extends AbsInteger {
    int num;
    public IntInteger(int num) {
        this.num = num;
    }
    @Override
    public BigInteger toBigInteger() {
        return new BigInteger(toString());
    }
    @Override
    public String toString() {
        return String.valueOf(num);
    }
}
