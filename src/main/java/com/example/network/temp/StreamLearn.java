package com.example.network.temp;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program com.example.network.temp
 * @description stream
 * @auther Mr.Xiong
 * @create 2021-02-18 10:1
 */
public class StreamLearn {
    public static void main(String[] args) {
        List<Map<String, String>> list = new ArrayList<>();

        Map<String,String> map1 = new HashMap<>();
        Map<String,String> map2 = new HashMap<>();
        Map<String,String> map3 = new HashMap<>();
        Map<String,String> map4 = new HashMap<>();

        map1.put("OA", "1");
        map1.put("NAME", "张");

        map3.put("OA", "2");
        map3.put("NAME", "王");

        map4.put("OA", "xn_3");
        map4.put("NAME", "xn_刘");

        map2.put("OA", "xn_1");
        map2.put("NAME", "xn_张");

        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);

        //通过一次循环，如果同时存在正常账号(1)和(xn_1)账号的，去除xn账号的map，重新返回一个list
        Set<String> oaSet = new HashSet<>();
        List<Map<String, String>> newList = list.stream()
            .sorted(Comparator.comparing((Map<String,String> m) -> m.get("OA")))
            .map(m -> {
                String oa = m.get("OA");
                if (oa.startsWith("xn_")) {
                    if (oaSet.contains(oa.replace("xn_", ""))) {
                        m.clear();
                    } else {
                        m.entrySet().forEach(es -> es.setValue(es.getValue().replace("xn_", "")));
                    }
                } else {
                    oaSet.add(oa);
                }
                return m;
        })
        .filter(m -> !m.isEmpty())
        .collect(Collectors.toList());
        System.out.println(newList);
    }
}
