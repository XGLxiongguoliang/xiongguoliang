package com.example.network.temp.designpattern.structure.flyweight;

public class TestMain {
    public static void main(String[] args) {
        FlyweightFactory flyweightFactory = new FlyweightFactory();
        Flyweight a1 = flyweightFactory.getFlyweight("a");
        Flyweight a2 = flyweightFactory.getFlyweight("a");
        Flyweight a3 = flyweightFactory.getFlyweight("a");

        Flyweight b1 = flyweightFactory.getFlyweight("b");
        Flyweight b2 = flyweightFactory.getFlyweight("b");

        a1.operation(new UnsharedConcreteFlyweight("第一次调用a"));
        a2.operation(new UnsharedConcreteFlyweight("第二次调用a"));
        a3.operation(new UnsharedConcreteFlyweight("第三次调用a"));

        b1.operation(new UnsharedConcreteFlyweight("第一次调用b"));
        b2.operation(new UnsharedConcreteFlyweight("第二次调用b"));
    }
}
