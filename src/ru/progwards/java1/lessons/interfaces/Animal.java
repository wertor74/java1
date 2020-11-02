package ru.progwards.java1.lessons.interfaces;

import java.util.Arrays;
import java.util.Objects;

public class Animal implements FoodCompare, CompareWeight {
    double weight;

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) return true;
        if (anObject == null || getClass() != anObject.getClass()) return false;
        Animal animal = (Animal) anObject;
        return Double.compare(animal.weight, weight) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight);
    }

    public Animal(double weight) {
        this.weight = weight;
    }

    @Override
    public CompareResult compareWeight(CompareWeight smthHasWeigt) {
        Animal animal = (Animal) smthHasWeigt;
        switch (Double.compare(weight, animal.weight)) {
            case 1: return CompareResult.GREATER;
            case 0: return CompareResult.EQUAL;
            case -1: return CompareResult.LESS;
        }
        return null;
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
    public double getFood1kgPrice() {
        double price = 0;
        switch (getFoodKind()) {
            case UNKNOWN:
                price = 0;
                break;
            case HAY:
                price = 20;
                break;
            case CORN:
                price = 50;
                break;
        }
        return price;
    }
    public double getFoodPrice() {
        return calculateFoodWeight() * getFood1kgPrice();
    }
    @Override
    public int compareFoodPrice(Animal aminal) {
        return Double.compare(this.getFoodPrice(), aminal.getFoodPrice());
    }
    public String toStringFull() {
        return toString() + " " + calculateFoodWeight();
    }
    @Override
    public String toString() {
        return "I am " + getKind() + ", eat " + getFoodKind();
    }

    public static void main(String[] args) {
        Animal animal = new Animal(100.0);
        Animal cow = new Cow(200.0);
        Duck duck = new Duck(8.0);
        Hamster hamster = new Hamster(1.5);
        CompareWeight[] a = {cow, animal, duck, hamster};
        CompareWeight.sort(a);
        System.out.println(Arrays.toString(a));
    }
}