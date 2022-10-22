package com.example.network;

import com.example.network.service.UserRelationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class NetworkApplicationTests {

    @Autowired
    private UserRelationService userRelationService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void getUserRelationListByUser() {
        Map<String, List> result = userRelationService.getUserRelationListByUser(8);
        System.out.println(result);
    }

    @Test
    void operateStrRedis() {
        //设置一个值
        redisTemplate.opsForValue().set("xgl-第一个key", "xgl-第一个数据", 120, TimeUnit.SECONDS);
        //获取一个值
        System.out.println(redisTemplate.opsForValue().get("xgl-第一个key"));

        //批量设置值
        HashMap<String, String> map = new HashMap<>();
        map.put("key-1", "data-1");
        map.put("key-2", "data-2");
        map.put("key-3", "data-3");
        redisTemplate.opsForValue().multiSet(map);
        //批量获取值
        List<String> keyList = new ArrayList<>();
        keyList.add("key-1");
        keyList.add("key-2");
        System.out.println(redisTemplate.opsForValue().multiGet(keyList));
        keyList.add("key-3");
        System.out.println(redisTemplate.opsForValue().multiGet(keyList));
    }

    @Test
    void operateListRedis() {
//        redisTemplate.opsForList().rightPush("list", "x");
//        redisTemplate.opsForList().rightPush("list", "i");
//        redisTemplate.opsForList().rightPush("list", "o");
//        redisTemplate.opsForList().rightPush("list", "n");
//        redisTemplate.opsForList().rightPush("list", "g");

        redisTemplate.opsForList().rightPop("list");
        redisTemplate.opsForList().rightPop("list");
        redisTemplate.opsForList().rightPop("list");
        redisTemplate.opsForList().rightPop("list");
        redisTemplate.opsForList().rightPop("list");

    }
}
