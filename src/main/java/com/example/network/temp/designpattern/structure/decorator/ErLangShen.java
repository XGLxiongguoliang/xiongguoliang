package com.example.network.temp.designpattern.structure.decorator;

public class ErLangShen extends Change {
    public ErLangShen(SunWukong sunWukong) {
        super(sunWukong);
    }

    public void display() {
        tianYan();
        super.display();
    }

    public void tianYan() {
        System.out.println("我也可以用天眼");
    }
}
