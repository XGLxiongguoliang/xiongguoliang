package com.example.network.temp.designpattern.builder;

public class BuilderCreater implements Builder {

    private House house;

    public BuilderCreater() {
        this.house = new House();
    }

    @Override
    public void buildDiJi() {
        System.out.println("构建地基");
        house.setDiJi("地基");
    }

    @Override
    public void buildQiang() {
        System.out.println("构建屋顶");
        house.setQiang("屋顶");
    }

    @Override
    public void buildWuding() {
        System.out.println("构建屋顶");
        house.setWuding("屋顶");
    }

    @Override
    public void buildFenShua() {
        System.out.println("粉刷全屋");
        house.setFenshua("粉刷");
    }

    public House getHouse() {
        return house;
    }
}
