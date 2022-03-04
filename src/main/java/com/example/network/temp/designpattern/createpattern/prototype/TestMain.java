package com.example.network.temp.designpattern.createpattern.prototype;

public class TestMain {
    public static void main(String[] args) {
        Bike cloneBike = new Bike().clone();
        System.out.println(cloneBike);
    }
}
