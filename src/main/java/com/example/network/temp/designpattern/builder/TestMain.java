package com.example.network.temp.designpattern.builder;

public class TestMain {
    public static void main(String[] args) {
        BuilderCreater builderCreater = new BuilderCreater();
        builderCreater.buildDiJi();
        builderCreater.buildQiang();
        builderCreater.buildWuding();
        builderCreater.buildFenShua();

        House house = builderCreater.getHouse();
        System.out.println(house.toString());
    }
}
