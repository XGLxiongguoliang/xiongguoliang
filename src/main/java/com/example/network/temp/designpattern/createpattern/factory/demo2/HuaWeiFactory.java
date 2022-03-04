package com.example.network.temp.designpattern.createpattern.factory.demo2;

public class HuaWeiFactory implements PhoneFactory {
    @Override
    public Phone getPhone() {
        return new HuaWeiPhone();
    }
}
