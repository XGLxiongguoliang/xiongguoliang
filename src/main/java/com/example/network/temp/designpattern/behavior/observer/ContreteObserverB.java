package com.example.network.temp.designpattern.behavior.observer;

/**
 * @program com.example.network.temp.designpattern.behavior.observer
 * @description 具体观察者B
 * @auther Mr.Xiong
 * @create 2022-03-05 10:38:58
 */
public class ContreteObserverB implements Observer {
    @Override
    public void response() {
        System.out.println("观察者B收到变动");
    }
}
