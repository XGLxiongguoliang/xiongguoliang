package com.example.network.temp.designpattern.behavior.observer;

/**
 * @program com.example.network.temp.designpattern.behavior.observer
 * @description 主题具体类
 * @auther Mr.Xiong
 * @create 2022-03-05 10:34:02
 */
public class ConcreteSubject extends Subject {

    @Override
    public void notifyAllObserver() {
        System.out.println("具体目标发生改变");

        observerList.forEach(n -> {
            n.response();
        });
    }
}
