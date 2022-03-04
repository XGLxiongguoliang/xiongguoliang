package com.example.network.temp.designpattern.structure.flyweight;

public class ConcreteFlyweight implements Flyweight {

    private String key;

    public ConcreteFlyweight(String key) {
        this.key = key;
        System.out.println("具体享元模式---" + key + "---被创建");
    }

    @Override
    public void operation(UnsharedConcreteFlyweight info) {
        System.out.print("具体的享元信息---" + key);
        System.out.println("非享元信息---" + info.getInfo());
    }
}
