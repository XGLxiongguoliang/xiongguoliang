package com.example.network.temp.designpattern.behavior.visitor;

/**
 * @program com.example.network.temp.designpattern.behavior.visitor
 * @description 元素A
 * @auther Mr.Xiong
 * @create 2022-03-05 14:03:59
 */
public class ElementA implements Element {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String operationA() {
        return "具体元素A的操作";
    }
}
