package ru.progwards.java1.lessons.queues;

public class Calculate {
    public static double calculation1() {
        StackCalc.push(2.2);
        StackCalc.push(12.1);
        StackCalc.push(3.0);
        StackCalc.add();
        StackCalc.mul();
        return StackCalc.pop();
    }
    public static double calculation2() {
        StackCalc.push(737.22);
        StackCalc.push(24.0);
        StackCalc.add();
        StackCalc.push(55.6);
        StackCalc.push(12.1);
        StackCalc.sub();
        StackCalc.div();
        StackCalc.push(19.0);
        StackCalc.push(3.33);
        StackCalc.sub();
        StackCalc.push(13.001);
        StackCalc.push(9.2);
        StackCalc.sub();
        StackCalc.push(2.0);
        StackCalc.mul();
        StackCalc.push(87.0);
        StackCalc.add();
        StackCalc.mul();
        StackCalc.add();
        return StackCalc.pop();
    }

    public static void main(String[] args) {
        System.out.println(calculation2());
    }
}
