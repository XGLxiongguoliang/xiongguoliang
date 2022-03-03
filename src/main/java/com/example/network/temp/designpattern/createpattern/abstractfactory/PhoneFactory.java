package com.example.network.temp.designpattern.createpattern.abstractfactory;

public class PhoneFactory implements AbstractFactory{
    @Override
    public Phone getPhone(String type) {
        if ("HuaWei".equals(type)) {
            return new HuaWeiPhone();
        } else {
            return new XiaoMiPhone();
        }
    }

    @Override
    public Router getRouter(String type) {
        return null;
    }
}
