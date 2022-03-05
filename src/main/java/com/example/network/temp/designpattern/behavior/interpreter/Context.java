package com.example.network.temp.designpattern.behavior.interpreter;

/**
 * @program com.example.network.temp.designpattern.behavior.interpreter
 * @description 环境类
 * @auther Mr.Xiong
 * @create 2022-03-05 15:33:19
 */
public class Context {

    private String[] citys = new String[]{"北京", "天津", "河北"};
    private String[] peoples = new String[]{"老人", "妇女", "儿童"};
    private Expression cityAndPeople;

    public Context() {
        Expression city = new TerminalExpression(citys);
        Expression people = new TerminalExpression(peoples);
        cityAndPeople =  new NoTerminalExpression(city, people);
    }

    public void freeRide(String info) {
        if (cityAndPeople.interpret(info)) {
            System.out.println("您是" + info + ",享受免费服务");
        } else {
            System.out.println("您是" + info + ",需付费2元");
        }
    }
}
