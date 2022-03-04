package com.example.network.temp.designpattern.behavior.template;

public class TestMain {
    public static void main(String[] args) {
        AbstractClass abstractClass = new SpecificClass();
        abstractClass.templateMethod();
    }
}
