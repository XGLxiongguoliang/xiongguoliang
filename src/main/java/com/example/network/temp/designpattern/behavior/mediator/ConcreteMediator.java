package com.example.network.temp.designpattern.behavior.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * @program com.example.network.temp.designpattern.behavior.mediator
 * @description 中介具体类
 * @auther Mr.Xiong
 * @create 2022-03-05 11:03:37
 */
public class ConcreteMediator extends Mediator {
    protected List<Colleague> colleagueList = new ArrayList<>();

    @Override
    public void register(Colleague colleague) {
        if (!colleagueList.contains(colleague)) {
            colleagueList.add(colleague);
            colleague.setMediator(this);
        }
    }

    @Override
    public void relay(Colleague colleague) {
        colleagueList.forEach(n -> {
            if (!n.equals(colleague)) {
                n.receive();
            }
        });
    }
}
