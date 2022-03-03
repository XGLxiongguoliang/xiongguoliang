package com.example.network.temp.designpattern.createpattern.prototype;

import lombok.Data;

/**
 * @program com.example.network.temp.designpattern.prototype
 * @description 自行车类
 * @auther Mr.Xiong
 * @create 2022-02-26 10:58:21
 */
@Data
public class Bike implements Cloneable {
    private String pingpai;
    private String price;
    private String produceDate;

    public Bike() {
        this.pingpai = "凤凰";
        this.price = "600";
        this.produceDate = "2020-12-02 12:32:23";
    }

    public Bike clone() {
        Object result = null;
        try {
            result = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return (Bike) result;
    }

    @Override
    public String toString() {
        return "Bike{" +
                "pingpai='" + pingpai + '\'' +
                ", price='" + price + '\'' +
                ", produceDate='" + produceDate + '\'' +
                '}';
    }
}
