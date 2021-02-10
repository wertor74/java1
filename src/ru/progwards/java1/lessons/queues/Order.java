package ru.progwards.java1.lessons.queues;

public class Order implements Comparable<Order> {
    private double sum;
    private int num;
    public Order(double sum) {
        this.sum = sum;
        OrderQueue.count++;
        num = OrderQueue.count;
    }
    @Override
    public String toString() {
        return "Order{" +
                "sum=" + sum +
                ", num=" + num +
                '}';
    }
    public double getSum() {
        return sum;
    }
    public int getNum() {
        return num;
    }

    @Override
    public int compareTo(Order o) {
        return Integer.compare(num, o.num);
    }
}
