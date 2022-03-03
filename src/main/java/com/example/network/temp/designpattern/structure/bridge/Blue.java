package com.example.network.temp.designpattern.structure.bridge;

/**
 * @program com.example.network.temp.designpattern.structure.bridge
 * @description 蓝色
 * @auther Mr.Xiong
 * @create 2022-02-26 16:01:51
 */
public class Blue implements Color {
    @Override
    public void getColor() {
        System.out.println("我是蓝色的");
    }
}
