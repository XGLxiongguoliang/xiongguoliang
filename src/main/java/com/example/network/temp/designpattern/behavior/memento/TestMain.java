package com.example.network.temp.designpattern.behavior.memento;

public class TestMain {
    public static void main(String[] args) {
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();

        originator.setState("第一步");
        System.out.println(originator.getState());

        System.out.println("管理备忘录，并记录第一步");
        caretaker.setMemento(originator.createMemento());

        originator.setState("第二步");
        System.out.println(originator.getState());

        originator.setState("第三步");
        System.out.println(originator.getState());

        System.out.println("通过备忘录返回到第一步");
        originator.setState(caretaker.getMemento().getState());
        System.out.println(originator.getState());
    }
}
