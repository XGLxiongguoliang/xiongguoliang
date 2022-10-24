package com.example.network;

import com.example.network.service.UserRelationService;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
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

    @Autowired
    RedissonClient redissonClien;

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

//        redisTemplate.opsForList().rightPush("list", "x");

        System.out.println(redisTemplate.opsForList().size("list"));
        System.out.println(redisTemplate.opsForList().index("list", 0));
        System.out.println(redisTemplate.opsForList().index("list", 1));
        System.out.println(redisTemplate.opsForList().index("list", 2));
        System.out.println(redisTemplate.opsForList().index("list", 3));
        System.out.println(redisTemplate.opsForList().index("list", 4));

        System.out.println(redisTemplate.opsForList().range("list", 0, -1));

//        redisTemplate.opsForList().rightPop("list");
//        redisTemplate.opsForList().rightPop("list");
//        redisTemplate.opsForList().rightPop("list");
//        redisTemplate.opsForList().rightPop("list");
//        redisTemplate.opsForList().rightPop("list");

    }

    @Test
    void operateSetRedis() {
//        redisTemplate.opsForSet().add("set", "1", "2", "1", "3", "4");

        System.out.println(redisTemplate.opsForSet().members("set"));
        System.out.println(redisTemplate.opsForSet().isMember("set", "1"));
    }

    @Test
    void operateZSetRedis() {
        redisTemplate.opsForZSet().add("zset", "x", 1);
        redisTemplate.opsForZSet().add("zset", "i", 1);
        redisTemplate.opsForZSet().add("zset", "o", 1);
        redisTemplate.opsForZSet().add("zset", "n", 1);
        redisTemplate.opsForZSet().add("zset", "g", 1);

        System.out.println(redisTemplate.opsForZSet().popMax("zset"));
    }

    @Test
    void operateMapRedis() {
//        redisTemplate.opsForHash().put("hash", "x", "x");
//        redisTemplate.opsForHash().put("hash", "i", "i");
//        redisTemplate.opsForHash().put("hash", "o", "o");
//        redisTemplate.opsForHash().put("hash", "n", "n");
//        redisTemplate.opsForHash().put("hash", "g", "g");
//
//        redisTemplate.opsForHash().delete("hash", "xiong");

        System.out.println(redisTemplate.opsForHash().entries("hash"));
        System.out.println(redisTemplate.opsForHash().keys("hash"));
        System.out.println(redisTemplate.opsForHash().get("hash", "g"));
    }

    @Test
    void operateMoreThread() {
        for (int i = 0; i < 100; i++) {
            Integer count = i;
            new Thread() {
                @Override
                public void run() {
                    Thread.currentThread().setName("name---" + count);
                    System.out.println(Thread.currentThread().getName() + "---线程开启");
                    operateRedisson();
                }
            }.start();
        }
    }

    private void operateRedisson() {
        RLock lock = redissonClien.getLock("redisson-test-xgl");
        System.out.println(Thread.currentThread().getName() + "---get lock ... start");
        if (!lock.tryLock()) {
            System.out.println(Thread.currentThread().getName() + "---获取锁失败---锁被锁着的");
        } else {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "---获取到锁");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + "---准备释放锁");
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + "---释放锁");
            }
        }
    }
}
