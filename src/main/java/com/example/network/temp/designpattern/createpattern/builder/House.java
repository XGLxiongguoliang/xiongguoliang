package com.example.network.temp.designpattern.createpattern.builder;

import lombok.Data;

@Data
public class House {
    private String diJi;
    private String qiang;
    private String wuding;
    private String fenshua;

    public String getDiJi() {
        return diJi;
    }

    public void setDiJi(String diJi) {
        this.diJi = diJi;
    }

    public String getQiang() {
        return qiang;
    }

    public void setQiang(String qiang) {
        this.qiang = qiang;
    }

    public String getWuding() {
        return wuding;
    }

    public void setWuding(String wuding) {
        this.wuding = wuding;
    }

    public String getFenshua() {
        return fenshua;
    }

    public void setFenshua(String fenshua) {
        this.fenshua = fenshua;
    }
}
