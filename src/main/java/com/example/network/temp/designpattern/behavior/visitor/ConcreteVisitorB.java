package com.example.network.temp.designpattern.behavior.visitor;

/**
 * @program com.example.network.temp.designpattern.behavior.visitor
 * @description 访问者具体类A
 * @auther Mr.Xiong
 * @create 2022-03-05 13:58:43
 */
public class ConcreteVisitorB implements Visitor {
    @Override
    public void visit(ElementA elementA) {
        System.out.println("具体访问者B---" + elementA.operationA());
    }

    @Override
    public void visit(ElementB elementB) {
        System.out.println("具体访问者B---" + elementB.operationB());
    }
}
