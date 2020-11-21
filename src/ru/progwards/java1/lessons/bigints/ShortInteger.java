package ru.progwards.java1.lessons.bigints;

import java.math.BigInteger;

public class ShortInteger extends AbsInteger {
    short num;
    public ShortInteger(short num) {
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
