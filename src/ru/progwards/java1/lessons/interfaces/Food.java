package ru.progwards.java1.lessons.interfaces;

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

    public static void main(String[] args) {
        System.out.println(new Food(100).compareWeight(new Food(200)));
    }
}