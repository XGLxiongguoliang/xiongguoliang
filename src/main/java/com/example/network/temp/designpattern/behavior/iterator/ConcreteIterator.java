package com.example.network.temp.designpattern.behavior.iterator;

import java.util.List;

/**
 * @program com.example.network.temp.designpattern.behavior.iterator
 * @description 迭代器具体类
 * @auther Mr.Xiong
 * @create 2022-03-05 11:42:06
 */
public class ConcreteIterator implements Iterator {

    private List<Object> list;

    private int index = -1;

    public ConcreteIterator(List<Object> list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        if (index < list.size() - 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object getFirst() {
        return list.get(0);
    }

    @Override
    public Object next() {
        Object next = null;
        if (this.hasNext()) {
            next = list.get(++index);
        }
        return next;
    }
}
