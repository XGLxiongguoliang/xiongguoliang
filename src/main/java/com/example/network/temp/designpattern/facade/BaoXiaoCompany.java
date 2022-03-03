package com.example.network.temp.designpattern.facade;

public class BaoXiaoCompany {
    private CheBaoXian cheBaoXian = new CheBaoXian();
    private RenshouBaoXian renshouBaoXian = new RenshouBaoXian();
    private YangLaoBaoXian yangLaoBaoXian = new YangLaoBaoXian();

    public void buyBaoXian() {
        cheBaoXian.cheBaoXian();
        renshouBaoXian.renshouBaoXian();
        yangLaoBaoXian.yangLaoBaoXian();
    }
}
