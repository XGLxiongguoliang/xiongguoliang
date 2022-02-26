package com.example.network.temp.designpattern.abstractfactory;

import lombok.Data;

@Data
public class XiaoMiRouter implements Router {
    @Override
    public void receiveNet() {
        System.out.println("小米路由器");
    }
}
