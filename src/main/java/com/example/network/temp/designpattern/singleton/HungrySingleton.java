package com.example.network.temp.designpattern.singleton;

/**
 * @program com.example.network.temp.designpattern.singleton
 * @description 饿汉式单例模式
 * @auther Mr.Xiong
 * @create 2022-02-26 10:47:02
 */
public class HungrySingleton {
    private static final HungrySingleton instance = new HungrySingleton();

    private HungrySingleton() {

    }

    public static synchronized HungrySingleton getInstance() {
        return instance;
    }
}
