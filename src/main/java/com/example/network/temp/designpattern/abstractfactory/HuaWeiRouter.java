package com.example.network.temp.designpattern.abstractfactory;

import lombok.Data;

@Data
public class HuaWeiRouter implements Router {
    @Override
    public void receiveNet() {
        System.out.println("华为路由器");
    }
}
