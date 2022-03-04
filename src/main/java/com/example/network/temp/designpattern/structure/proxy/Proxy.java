package com.example.network.temp.designpattern.structure.proxy;

/**
 * @program com.example.network.temp.designpattern.structure.proxy
 * @description 代理类
 * @auther Mr.Xiong
 * @create 2022-02-26 14:41:33
 */
public class Proxy implements Subject {

    private RealSubject realSubject;

    public void preCreate() {
        System.out.println("创建项目之前哦。。。");
    }

    @Override
    public void createSubject() {
        if (realSubject == null) {
            realSubject = new RealSubject();
        }

        preCreate();
        realSubject.createSubject();
        postCreate();
    }

    public void postCreate() {
        System.out.println("创建项目之后哦。。。");
    }
}
