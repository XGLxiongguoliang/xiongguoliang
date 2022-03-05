package com.example.network.temp.designpattern.behavior.visitor;

public class TestMain {
    public static void main(String[] args) {
        ObjectStructure objectStructure = new ObjectStructure();
        Element elementA = new ElementA();
        Element elementB = new ElementB();
        objectStructure.add(elementA);
        objectStructure.add(elementB);

        ConcreteVisitorA visitorA = new ConcreteVisitorA();
        ConcreteVisitorB visitorB = new ConcreteVisitorB();

        objectStructure.accept(visitorA);
        objectStructure.accept(visitorB);
     }
}
