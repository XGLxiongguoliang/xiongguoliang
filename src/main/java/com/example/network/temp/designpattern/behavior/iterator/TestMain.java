package com.example.network.temp.designpattern.behavior.iterator;

public class TestMain {
    public static void main(String[] args) {
        Aggregate aggregate = new ConcreteAggregate();
        aggregate.add("五虎上将-关羽");
        aggregate.add("五虎上将-张飞");
        aggregate.add("五虎上将-赵云");
        aggregate.add("五虎上将-黄忠");
        aggregate.add("五虎上将-马超");
        Iterator iterator = aggregate.getIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("第一虎将---" + iterator.getFirst());
    }
}
