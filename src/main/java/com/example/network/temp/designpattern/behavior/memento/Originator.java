package com.example.network.temp.designpattern.behavior.memento;

/**
 * @program com.example.network.temp.designpattern.behavior.memento
 * @description 发起人
 * @auther Mr.Xiong
 * @create 2022-03-05 14:52:14
 */
public class Originator {
    private String state;

    public Memento createMemento() {
        return new Memento(state);
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
