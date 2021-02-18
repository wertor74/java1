package ru.progwards.java1.lessons.queues;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class OrderQueue {
    static int count; // счётчик номера заказа
    static PriorityQueue<Order> addOrder = new PriorityQueue();

    public static void add(Order order) {
        addOrder.offer(order);
    }
    public static Order get() {
        while (!addOrder.isEmpty()) return addOrder.poll();
        return null;
    }

    public static void main(String[] args) {
        add(new Order(15000.99));
        add(new Order(999.50));
        add(new Order(21999.99));
        add(new Order(4995.95));
        add(new Order(50555.55));
        add(new Order(10001.01));
        add(new Order(75575.77));
        add(new Order(19999.99));
        add(new Order(9500.22));
        System.out.println(addOrder);
        for (int i = 1; i <= 9; i++) {
            System.out.println(get());
        }
        System.out.println(addOrder);
    }
}