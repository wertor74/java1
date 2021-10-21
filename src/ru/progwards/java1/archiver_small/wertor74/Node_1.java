package ru.progwards.java1.archiver_small.wertor74;

public class Node_1 {
    public Integer frequency;
    public Byte name;
    public Node_1 leftChild;
    public Node_1 rightChild;
    public String code = "";
    public Node_1 (Byte name, Integer frequency) {
        this.name = name;
        this.frequency = frequency;
    }
    public Byte getName() {
        return name;
    }
    public int getFrequency() {
        return frequency;
    }
    public boolean isLeaf() {
        return leftChild == null && rightChild == null;
    }

    @Override
    public String toString() {
        return "Node_1{" +
                "name=" + name +
                ", frequency=" + frequency +
                /*", leftChild=" + leftChild +
                ", rightChild=" + rightChild +*/
                ", code=" + code +
                '}';
    }
}
