package com.example.network.temp.designpattern.structure.bridge;

/**
 * @program com.example.network.temp.designpattern.structure.bridge
 * @description 红色
 * @auther Mr.Xiong
 * @create 2022-02-26 16:01:22
 */
public class Red implements Color {
    @Override
    public void getColor() {
        System.out.println("我是红色的");
    }
}
