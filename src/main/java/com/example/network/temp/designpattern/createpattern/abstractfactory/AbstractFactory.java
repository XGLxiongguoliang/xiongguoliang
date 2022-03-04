package com.example.network.temp.designpattern.createpattern.abstractfactory;

public interface AbstractFactory {
    public Phone getPhone(String type);
    public Router getRouter(String type);
}
