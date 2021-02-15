package ru.progwards.java1.lessons.queues;

import java.util.ArrayDeque;
import java.util.Deque;

public class StackCalc {
    static Deque<Double> stack = new ArrayDeque<>();
    public static void push(double value) {
        stack.push(value);
    }
    public static double pop() {
        return stack.pop();
    }
    public static void add() {
        double result = pop() + pop();
        push(result);
    }
    public static void sub() {
        double first = pop();
        double result = pop() - first;
        push(result);
    }
    public static void mul() {
        double result = pop() * pop();
        push(result);
    }
    public static void div() {
        double first = pop();
        double result = pop() / first;
        push(result);
    }

    public static void main(String[] args) {
        push(1.0);
        push(2.0);
        push(3.0);
        div();
        System.out.println(stack);
    }
}
