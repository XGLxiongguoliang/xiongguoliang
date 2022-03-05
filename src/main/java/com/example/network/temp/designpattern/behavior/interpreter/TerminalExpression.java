package com.example.network.temp.designpattern.behavior.interpreter;

import java.util.HashSet;
import java.util.Set;

/**
 * @program com.example.network.temp.designpattern.behavior.interpreter
 * @description 终结表达式具体类
 * @auther Mr.Xiong
 * @create 2022-03-05 15:29:30
 */
public class TerminalExpression implements Expression {

    private Set<String> set = new HashSet<>();

    public TerminalExpression(String[] datas) {
        for (String data : datas) {
            set.add(data);
        }
    }

    @Override
    public boolean interpret(String info) {
        if (set.contains(info)) {
            return true;
        } else {
            return false;
        }
    }
}
