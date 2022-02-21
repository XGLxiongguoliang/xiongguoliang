package com.example.network.temp.designpattern.factory.demo1;

public class PhoneFactory {
    public XiaoMiPhone getXiaoMiPhone() {
        return new XiaoMiPhone();
    }

    public HuaWeiPhone getHuaWeiPhone() {
        return new HuaWeiPhone();
    }
}
