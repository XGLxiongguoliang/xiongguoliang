package com.example.network.temp.designpattern.behavior.mediator;

public class TestMain {
    public static void main(String[] args) {
        Mediator mediator = new ConcreteMediator();
        Colleague colleagueA = new ConcreteColleagueA();
        Colleague colleagueB = new ConcreteColleagueB();

        mediator.register(colleagueA);
        mediator.register(colleagueB);

        colleagueA.send();
        colleagueB.send();
    }
}
