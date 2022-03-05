package com.example.network.temp.designpattern.behavior.visitor;

/**
 * @program com.example.network.temp.designpattern.behavior.visitor
 * @description 元素B
 * @auther Mr.Xiong
 * @create 2022-03-05 14:03:59
 */
public class ElementB implements Element {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String operationB() {
        return "具体元素B的操作";
    }
}
