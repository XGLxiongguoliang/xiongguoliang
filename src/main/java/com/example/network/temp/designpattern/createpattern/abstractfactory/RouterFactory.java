package com.example.network.temp.designpattern.createpattern.abstractfactory;

public class RouterFactory implements AbstractFactory {
    @Override
    public Phone getPhone(String type) {
        return null;
    }

    @Override
    public Router getRouter(String type) {
        if ("HuaWei".equals(type)) {
            return new HuaWeiRouter();
        } else {
            return new XiaoMiRouter();
        }
    }
}
