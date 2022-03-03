package com.example.network.temp.designpattern.createpattern.factory.demo1;

public class TestMain {
    public static void main(String[] args) {
        /*XiaoMiPhone xiaoMiPhone = new XiaoMiPhone();
        xiaoMiPhone.call();
        HuaWeiPhone huaWeiPhone = new HuaWeiPhone();
        huaWeiPhone.call();*/

        PhoneFactory phoneFactory = new PhoneFactory();
        phoneFactory.getXiaoMiPhone().call();
        phoneFactory.getHuaWeiPhone().call();


    }
}
