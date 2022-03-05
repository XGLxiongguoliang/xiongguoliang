package com.example.network.temp.designpattern.behavior.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @program com.example.network.temp.designpattern.behavior.observer
 * @description 主题抽象类
 * @auther Mr.Xiong
 * @create 2022-03-05 10:30:11
 */
public abstract class Subject {
    protected List<Observer> observerList = new ArrayList<>();

    public void add(Observer observer) {
        observerList.add(observer);
    }

    public void remove(Observer observer) {
        observerList.remove(observer);
    }

    public abstract void notifyAllObserver();
}
