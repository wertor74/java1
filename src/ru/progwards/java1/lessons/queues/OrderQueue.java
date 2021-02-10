package ru.progwards.java1.lessons.queues;

import java.util.PriorityQueue;

public class OrderQueue {
    static int count; // счётчик номера заказа
    static PriorityQueue<Order> addOrder_1 = new PriorityQueue();
    static PriorityQueue<Order> addOrder_2 = new PriorityQueue();
    static PriorityQueue<Order> addOrder_3 = new PriorityQueue();

    public static void add(Order order) {
        if (order.getSum() <= 10000.00) {
            addOrder_3.offer(order);
        } else if (order.getSum() > 10000.00 && order.getSum() <= 20000.00) {
            addOrder_2.offer(order);
        } else {
            addOrder_1.offer(order);
        }
    }
    public static Order get() {
        while (!addOrder_1.isEmpty()) return addOrder_1.poll();
        while (!addOrder_2.isEmpty()) return addOrder_2.poll();
        while (!addOrder_3.isEmpty()) return addOrder_3.poll();
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
        System.out.println(addOrder_1);
        System.out.println(addOrder_2);
        System.out.println(addOrder_3);
        for (int i = 1; i <= 9; i++) {
            System.out.println(get());
        }
    }
}
