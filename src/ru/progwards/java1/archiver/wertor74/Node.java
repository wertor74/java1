package ru.progwards.java1.archiver.wertor74;

public class Node {
    public Integer frequency;
    public Byte name;
    public Node leftChild;
    public Node rightChild;
    public String code = "";
    public Node (Byte name, Integer frequency) {
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
        return "Node{" +
                "name=" + name +
                ", frequency=" + frequency +
                /*", leftChild=" + leftChild +
                ", rightChild=" + rightChild +*/
                ", code=" + code +
                '}';
    }
}
