package com.example.network.temp.designpattern.structure.composite;

import lombok.Data;

@Data
public class Leaf implements Component {
    private String name;

    public Leaf(String name) {
        this.name = name;
    }

    @Override
    public void operation() {
        System.out.println("叶子节点" + name + "被访问");
    }
}
