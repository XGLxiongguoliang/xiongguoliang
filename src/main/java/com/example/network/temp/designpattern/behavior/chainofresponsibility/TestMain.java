package com.example.network.temp.designpattern.behavior.chainofresponsibility;

public class TestMain {
    public static void main(String[] args) {
        Handler handlerA = new ConcreteHandlerA();
        Handler handlerB = new ConcreteHandlerB();

        handlerB.setHandler(handlerA);

        //handlerA.handlerRequest("A");
        handlerB.handlerRequest("A");
    }
}
