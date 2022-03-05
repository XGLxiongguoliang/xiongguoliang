package com.example.network.temp.designpattern.behavior.state;

/**
 * @program com.example.network.temp.designpattern.behavior.state
 * @description 环境类
 * @auther Mr.Xiong
 * @create 2022-03-05 09:58:50
 */
public class Context {
    private State state;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Context() {
        this.name = "A";
        this.state = new ConcreteStateA();
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void handle() {
        state.handle(this);
    }
}
