package com.example.network.temp.designpattern.structure.proxy;

/**
 * @program com.example.network.temp.designpattern.structure.proxy
 * @description 真实的项目类
 * @auther Mr.Xiong
 * @create 2022-02-26 14:39:28
 */
public class RealSubject implements Subject {
    @Override
    public void createSubject() {
        System.out.println("创建一个真实的项目");
    }
}
