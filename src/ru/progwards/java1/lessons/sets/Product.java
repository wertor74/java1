package ru.progwards.java1.lessons.sets;

public class Product {
    private String code;
    public Product(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }

    public String getCode() {
        return code;
    }
}
