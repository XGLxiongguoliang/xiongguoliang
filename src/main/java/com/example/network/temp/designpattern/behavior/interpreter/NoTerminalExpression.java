package com.example.network.temp.designpattern.behavior.interpreter;

/**
 * @program com.example.network.temp.designpattern.behavior.interpreter
 * @description 不终结表达式具体类
 * @auther Mr.Xiong
 * @create 2022-03-05 15:29:30
 */
public class NoTerminalExpression implements Expression {

    private Expression city;
    private Expression people;

    public NoTerminalExpression(Expression city, Expression people) {
       this.city = city;
       this.people = people;
    }

    @Override
    public boolean interpret(String info) {
        String[] strs = info.split("的");
        return city.interpret(strs[0]) && people.interpret(strs[1]);
    }
}
