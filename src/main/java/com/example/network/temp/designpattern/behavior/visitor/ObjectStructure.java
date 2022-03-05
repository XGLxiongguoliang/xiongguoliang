package com.example.network.temp.designpattern.behavior.visitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @program com.example.network.temp.designpattern.behavior.visitor
 * @description 对象结构类
 * @auther Mr.Xiong
 * @create 2022-03-05 14:13:20
 */
public class ObjectStructure {
    private List<Element> elementList = new ArrayList<>();

    public void accept(Visitor visitor) {
        Iterator iterator = elementList.iterator();
        while(iterator.hasNext()) {
            Element element = (Element) iterator.next();
            element.accept(visitor);
        }
    }

    public void add(Element element) {
        elementList.add(element);
    }

    public void remove(Element element) {
        elementList.remove(element);
    }
}
