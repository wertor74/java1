package ru.progwards.java1.lessons.classes;

public class Animal {
    double weight;
    public Animal(double weight) {
        this.weight = weight;
    }
    enum AnimalKind {
        ANIMAL,
        COW,
        HAMSTER,
        DUCK
    }
    public AnimalKind getKind() {
        return AnimalKind.ANIMAL;
    }
    enum FoodKind {
        UNKNOWN,
        HAY,
        CORN
    }
    public FoodKind getFoodKind() {
        return FoodKind.UNKNOWN;
    }
    public double getWeight() {
        return weight;
    }
    public double getFoodCoeff() {
        return 0.02;
    }
    public double calculateFoodWeight() {
        return getWeight() * getFoodCoeff();
    }
    public String toStringFull() {
        return toString() + " " + calculateFoodWeight();
    }
    @Override
    public String toString() {
        return "I am " + getKind() + ", eat " + getFoodKind();
    }

    public static void main(String[] args) {
        System.out.println(new Hamster(2.0));
    }
}