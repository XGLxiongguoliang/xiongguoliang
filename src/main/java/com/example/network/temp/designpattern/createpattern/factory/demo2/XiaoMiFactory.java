package com.example.network.temp.designpattern.createpattern.factory.demo2;

public class XiaoMiFactory implements PhoneFactory {
    @Override
    public Phone getPhone() {
        return new XiaoMiPhone();
    }
}
