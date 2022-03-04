package com.example.network.temp.designpattern.structure.composite;

public class TestMain {
    public static void main(String[] args) {
        Composite c1 = new Composite();
        Composite c2 = new Composite();
        Component l1 = new Leaf("1");
        Component l2 = new Leaf("2");
        Component l3 = new Leaf("3");
        Component l4 = new Leaf("4");
        Component l5 = new Leaf("5");
        c1.add(l1);
        c1.add(l2);
        c1.add(l3);

        c2.add(l4);
        c2.add(l5);

        c1.operation();
    }
}
