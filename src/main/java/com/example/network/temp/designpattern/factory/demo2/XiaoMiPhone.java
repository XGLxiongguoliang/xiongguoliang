package com.example.network.temp.designpattern.factory.demo2;

import lombok.Data;

@Data
public class XiaoMiPhone implements Phone {

    public void call() {
        System.out.println("小米手机打电话");
    }

}
