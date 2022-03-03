package com.example.network.temp.designpattern.structure.bridge;

/**
 * @program com.example.network.temp.designpattern.structure.bridge
 * @description lv
 * @auther Mr.Xiong
 * @create 2022-02-26 16:03:33
 */
public class LV implements Brand {
    @Override
    public void getBrand() {
        System.out.println("我是LV包包");
    }
}
