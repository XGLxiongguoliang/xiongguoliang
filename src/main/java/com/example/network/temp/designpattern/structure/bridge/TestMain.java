package com.example.network.temp.designpattern.structure.bridge;

import com.example.network.temp.designpattern.structure.adapter.object.Adapter;
import com.example.network.temp.designpattern.structure.adapter.object.XiaoMiPhoneLine;

public class TestMain {
    public static void main(String[] args) {
        Brand brand = new Dior();
        Color color = new Red();

        Bag bag = new HandBag();
        bag.setBrand(brand);
        bag.setColor(color);

        bag.getName();
        bag.brand.getBrand();
        bag.color.getColor();
    }
}
