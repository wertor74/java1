package ru.progwards.java1.lessons.interfaces;

import java.util.Arrays;

public class Food implements CompareWeight{
    private int weight;

    public Food (int weight) {
        this.weight = weight;
    }
    public int getWeight() {
        return weight;
    }

    @Override
    public CompareResult compareWeight(CompareWeight smthHasWeigt) {
        Food food = (Food) smthHasWeigt;
        switch (Integer.compare(this.weight, food.weight)) {
            case 1: return CompareResult.GREATER;
            case 0: return CompareResult.EQUAL;
            case -1: return CompareResult.LESS;
        }
        return null;
    }
    public String toString() {
        return "" + getWeight();
    }

    public static void main(String[] args) {
        Food food1 = new Food(0);
        Food food2 = new Food(25);
        Food food3 = new Food(118);
        Food food4 = new Food(201);
        Food food5 = new Food(5009);
        CompareWeight[] a = {food4, food5, food3, food2, food1};
        ArraySort.sort(a);
        System.out.println(Arrays.toString(a));
    }
}