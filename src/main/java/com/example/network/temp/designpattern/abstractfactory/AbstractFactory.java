package com.example.network.temp.designpattern.abstractfactory;

public interface AbstractFactory {
    public Phone getPhone(String type);
    public Router getRouter(String type);
}
