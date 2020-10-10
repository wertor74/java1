package ru.progwards.java1.lessons.classes;

public class ComplexNum {
    int a, b;
    public ComplexNum (int a, int b) {
        this.a = a;
        this.b = b;
    }
    @Override
    public String toString() {
        return a + "+" + b + "i";
    }
    public ComplexNum add(ComplexNum num) {
        return new ComplexNum(num.a + num.a, num.b + num.b);
    }
    public ComplexNum sub(ComplexNum num) {
        return new ComplexNum(num.a - num.a, num.b - num.b);
    }
    public ComplexNum mul(ComplexNum num) {
        return new ComplexNum((num.a * num.a) - (num.b * num.b), (num.b * num.a) + (num.a * num.b));
    }
    public ComplexNum div(ComplexNum num) {
        return new ComplexNum(((num.a * num.a) + (num.b * num.b)) / ((num.a * num.a) + (num.b * num.b)),
                ((num.b * num.a) - (num.a * num.b)) / ((num.a * num.a) + (num.b * num.b)));
    }
    public static void main(String[] args) {
        System.out.println(new ComplexNum(1, 56));
        }
}