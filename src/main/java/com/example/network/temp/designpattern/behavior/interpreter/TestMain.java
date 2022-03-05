package com.example.network.temp.designpattern.behavior.interpreter;

public class TestMain {
    public static void main(String[] args) {
        Context context = new Context();
        context.freeRide("北京的老人");
        context.freeRide("天津的妇女");
        context.freeRide("河北的儿童");

        context.freeRide("北京的男人");
        context.freeRide("山东的青年");
    }
}
