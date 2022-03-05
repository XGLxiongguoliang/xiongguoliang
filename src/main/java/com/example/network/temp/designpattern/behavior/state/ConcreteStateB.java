package com.example.network.temp.designpattern.behavior.state;

/**
 * @program com.example.network.temp.designpattern.behavior.state
 * @description 环境B
 * @auther Mr.Xiong
 * @create 2022-03-05 10:00:48
 */
public class ConcreteStateB extends State {

    @Override
    public void handle(Context context) {
        System.out.println("当前状态是B");
        context.setName("B");
        context.setState(new ConcreteStateA());
    }
}
