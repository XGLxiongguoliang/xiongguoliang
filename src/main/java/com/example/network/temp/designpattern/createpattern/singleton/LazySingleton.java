package com.example.network.temp.designpattern.createpattern.singleton;

/**
 * @program com.example.network.temp.designpattern.singleton
 * @description 懒加载单例模式
 * @auther Mr.Xiong
 * @create 2022-02-26 10:40:35
 */
public class LazySingleton {
    private static LazySingleton instance = null;

    private LazySingleton() {

    }

    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
