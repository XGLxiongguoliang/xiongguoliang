package com.example.network.temp.designpattern.behavior.chainofresponsibility;

/**
 * @program com.example.network.temp.designpattern.behavior.chainofresponsibility
 * @description 抽象处理类
 * @auther Mr.Xiong
 * @create 2022-03-05 09:23:25
 */
public abstract class Handler {

    private Handler handler;

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public abstract void handlerRequest(String request);
}
