package com.example.network.temp.designpattern.behavior.memento;

/**
 * @program com.example.network.temp.designpattern.behavior.memento
 * @description 备忘录具体类
 * @auther Mr.Xiong
 * @create 2022-03-05 14:51:01
 */
public class Memento {
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
