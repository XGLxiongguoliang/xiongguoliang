package com.example.network.temp.designpattern.behavior.mediator;

/**
 * @program com.example.network.temp.designpattern.behavior.mediator
 * @description 具体同事类B
 * @auther Mr.Xiong
 * @create 2022-03-05 11:14:29
 */
public class ConcreteColleagueB extends Colleague {
    @Override
    public void receive() {
        System.out.println("同事B收到消息");
    }

    @Override
    public void send() {
        System.out.println("同事B发出消息");
        mediator.relay(this);
    }
}
