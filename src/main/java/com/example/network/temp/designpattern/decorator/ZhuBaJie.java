package com.example.network.temp.designpattern.decorator;

public class ZhuBaJie extends Change {
    public ZhuBaJie(SunWukong sunWukong) {
        super(sunWukong);
    }

    public void display() {
        jiuChiDingPa();
        super.display();
    }

    public void jiuChiDingPa() {
        System.out.println("我也可以用九齿钉耙了");
    }
}
