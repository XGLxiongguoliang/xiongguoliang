package com.example.network.temp.designpattern.createpattern.abstractfactory;

import lombok.Data;

@Data
public class HuaWeiPhone implements Phone {

    public void call() {
        System.out.println("华为手机打电话");
    }

}
