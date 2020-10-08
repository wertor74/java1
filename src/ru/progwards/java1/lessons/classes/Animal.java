package ru.progwards.java1.lessons.classes;

public class Animal {
    double weight;
    Animal(double weight) {
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
        return weight * getFoodCoeff();
    }
    public String toStringFull() {
        return "I am " + getKind() + ", eat " + getFoodKind() + " " + calculateFoodWeight();
    }
    @Override
    public String toString() {
        return toStringFull();
    }

    public static void main(String[] args) {
        System.out.println(new Hamster(2.0));
    }
}