package com.example.network.temp.designpattern.factory.demo2;

public class TestMain {
    public static void main(String[] args) {
        new HuaWeiFactory().getPhone().call();

        new XiaoMiFactory().getPhone().call();
    }
}
