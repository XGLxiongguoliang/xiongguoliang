package com.example.network.temp.designpattern.structure.flyweight;

import lombok.Data;

@Data
public class UnsharedConcreteFlyweight {
    private String info;

    public UnsharedConcreteFlyweight(String info) {
        this.info = info;
    }
}
