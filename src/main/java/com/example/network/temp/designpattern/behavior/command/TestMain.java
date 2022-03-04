package com.example.network.temp.designpattern.behavior.command;

public class TestMain {
    public static void main(String[] args) {
        Command command = new ConcreteCommand();
        Invoker invoker = new Invoker(command);
        invoker.call();
    }
}
