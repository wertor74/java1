package ru.progwards.java1.lessons.queues;

public class Order implements Comparable<Order> {
    private double sum;
    private int num;
    private int klass; // класс суммы заказа
    public Order(double sum) {
        this.sum = sum;
        OrderQueue.count++;
        num = OrderQueue.count;
        if (sum > 0 && sum <= 10000.00) {
            klass = 3;
        } else if (sum > 10000.00 && sum <= 20000.00) {
            klass = 2;
        } else {
            klass = 1;
        }
    }
    @Override
    public String toString() {
        return "Order{" +
                "sum=" + sum +
                ", num=" + num +
                ", klass=" + klass + '}';
    }
    public double getSum() {
        return sum;
    }
    public int getNum() {
        return num;
    }

    @Override
    public int compareTo(Order o) {
        if (klass == o.klass) {
            return Integer.compare(num, o.num);
        } else {
            return Double.compare(o.sum, sum);
        }
    }
}
